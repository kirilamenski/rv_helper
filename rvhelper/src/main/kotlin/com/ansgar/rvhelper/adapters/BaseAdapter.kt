package com.ansgar.rvhelper.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ansgar.rvhelper.R
import com.ansgar.rvhelper.holders.BaseViewHolder
import com.ansgar.rvhelper.holders.DefaultViewHolder
import com.ansgar.rvhelper.utils.RvAdapterDiffUtil
import com.ansgar.rvhelper.utils.ViewHoldersUtil
import kotlin.collections.ArrayList

/**
 * Basic Adapter class. It is contain basic implementations of [AdapterActions].
 * @param VM is View Model which is in the [items].
 */
abstract class BaseAdapter<VM> : RecyclerView.Adapter<BaseViewHolder<*>>(), AdapterActions<VM> {
    /**
     * Main list which contain View Models [VM]. It is internal to use it only in package.
     * All necessary methods should be defined in [AdapterActions] and implemented in base adapter
     * class or in children classes([SingleTypeAdapter] or [MultipleTypesAdapter]).
     */
    internal val items = ArrayList<VM>()
    internal var viewHoldersUtil: ViewHoldersUtil = ViewHoldersUtil()
    var onBindViewHolder: ((holder: BaseViewHolder<*>, position: Int) -> Unit)? = null
    var onItemsSame: ((oldItem: VM, newItem: VM) -> Boolean)? = null
    var onContentSame: ((oldItem: VM, newItem: VM) -> Boolean)? = null

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> =
        viewHoldersUtil.getOnVhCreated(viewType)?.invoke(
            LayoutInflater.from(parent.context).inflate(
                viewType,
                parent,
                false
            )
        ) ?: DefaultViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_default,
                parent,
                false
            )
        )

    @CallSuper
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        holder as BaseViewHolder<VM>
        val item = items[position]
//        rvAdapterHelper.getOnBindVh<BaseViewHolder<ViewHolderItem>, ViewHolderItem>(
//            getItemViewType(position)
//        )?.invoke(holder, item, position)
        holder.bindModel(item)
        onBindViewHolder?.let { it(holder, position) }
    }

    /**
     * Create an instance of [ViewHoldersUtil] a class that contains view holders and listeners.
     * @param build instance of [ViewHoldersUtil]
     */
    override fun viewHoldersUtil(build: ViewHoldersUtil.() -> Unit) {
        viewHoldersUtil.build()
    }

    override fun addAll(items: List<VM>) {
        val size = this.items.size
        this.items.addAll(items)
        notifyItemRangeInserted(size, items.size)
    }

    /**
     * Use [DiffUtil] to make it easy to update list.
     * @param items new items.
     */
    override fun updateAll(items: List<VM>) {
        var diffResult: DiffUtil.DiffResult? = null
        if (onItemsSame != null && onContentSame != null) {
            diffResult = DiffUtil.calculateDiff(
                RvAdapterDiffUtil(
                    this.items,
                    items,
                    onItemsSame!!,
                    onContentSame!!
                )
            )
        }
        with(this.items) {
            clear()
            addAll(items)
        }
        diffResult?.dispatchUpdatesTo(this)
    }

    override fun add(item: VM, payload: Boolean) {
        add(item, items.size - 1, payload)
    }

    override fun add(item: VM, position: Int, payload: Boolean) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    override fun delete(item: VM) {
        val position = items.indexOf(item)
        if (position != -1) delete(position)
    }

    override fun delete(position: Int) {
        if (isPositionExists(position)) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun update(item: VM, position: Int, payload: Boolean) {
        if (isPositionExists(position)) {
            items[position] = item
            notifyItemChanged(position, payload)
        }
    }

    /**
     * @return an item [VM] at specific [position]
     * @param position of the item in the list [items]
     */
    override fun getItemAt(position: Int): VM = items[position]

    /**
     * @param position index of view model in the [items]
     * @return true if [position] in range from 0 to [items.size]
     */
    private fun isPositionExists(position: Int) = position in 0 until items.size
}