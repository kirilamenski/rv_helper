package com.ansgar.rvhelper.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ansgar.rvhelper.R
import com.ansgar.rvhelper.holders.BaseViewHolder
import com.ansgar.rvhelper.holders.ErrorViewHolder
import com.ansgar.rvhelper.scroll.RvAdapterObserver
import com.ansgar.rvhelper.utils.RvAdapterDiffUtil
import com.ansgar.rvhelper.utils.ViewHoldersUtil
import kotlin.collections.ArrayList

/**
 * Basic Adapter class. It is contain basic implementations of [AdapterActions].
 * @param VM is View Model which is in the [items].
 */
abstract class BaseAdapter<VM>(val viewHoldersUtil: ViewHoldersUtil) :
    RecyclerView.Adapter<BaseViewHolder<*>>(), AdapterActions<VM> {
    /**
     * Main list which contain View Models [VM].
     * All necessary methods could be defined in [AdapterActions] and implemented in base adapter
     * class or in children classes([SingleTypeAdapter] or [MultipleTypesAdapter]).
     */
    val items = ArrayList<VM>()
    var onItemsSame: ((oldItem: VM, newItem: VM) -> Boolean)? = null
    var onContentSame: ((oldItem: VM, newItem: VM) -> Boolean)? = null
    var onBindViewHolder: ((holder: BaseViewHolder<*>, position: Int) -> Unit)? = null
    var onScrollingObserver: RvAdapterObserver? = null

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> =
        viewHoldersUtil.getOnVhCreated(viewType)?.invoke(
            LayoutInflater.from(parent.context).inflate(
                viewType,
                parent,
                false
            )
        ) ?: ErrorViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_error,
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

    @CallSuper
    override fun addAll(items: ArrayList<VM>) {
        val size = this.items.size
        this.items.addAll(items)
        notifyItemRangeInserted(size, items.size)
        if (items.isEmpty()) stopScrolling()
    }

    /**
     * Use [DiffUtil] to make it easy to update list.
     * @param items new items.
     */
    override fun updateAll(items: ArrayList<VM>) {
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

    override fun deleteLoadingViewHolder() {
    }

    /**
     * @return an item [VM] at specific [position]
     * @param position of the item in the list [items]
     */
    override fun getItemAt(position: Int): VM = items[position]

    override fun refresh() {
        items.clear()
        notifyDataSetChanged()
        onScrollingObserver?.onRefresh()
    }

    /**
     * @param position index of view model in the [items]
     * @return true if [position] in range from 0 to [items.size]
     */
    private fun isPositionExists(position: Int) = position in 0 until items.size

    private fun stopScrolling() {
        onScrollingObserver?.onNewItemEmpty()
    }
}
