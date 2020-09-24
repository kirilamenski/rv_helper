package com.ansgar.rvhelper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ansgar.rvhelper.models.ViewHolderItem
import java.util.*

open class RvAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private lateinit var rvAdapterHelper: RvHelper
    val items = LinkedList<ViewHolderItem>()
    var onBindViewHolder: ((holder: BaseViewHolder<*>, position: Int) -> Unit)? = null
    var onBottom: (() -> Unit)? = null
    var onItemsSame: ((oldItem: ViewHolderItem, newItem: ViewHolderItem) -> Boolean)? = null
    var onContentSame: ((oldItem: ViewHolderItem, newItem: ViewHolderItem) -> Boolean)? = null

    // TODO Find a way to use layoutRes from rv helper class
    override fun getItemViewType(position: Int): Int = items[position].layoutRes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> =
        rvAdapterHelper.getOnVhCreated(viewType)?.invoke(
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

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        holder as BaseViewHolder<ViewHolderItem>
        val item = items[position]
//        rvAdapterHelper.getOnBindVh<BaseViewHolder<ViewHolderItem>, ViewHolderItem>(
//            getItemViewType(position)
//        )?.invoke(holder, item, position)
        holder.bindModel(item)
        onBindViewHolder?.let { it(holder, position) }
    }

    override fun getItemCount(): Int = items.size

    fun setHelper(build: RvHelper.() -> Unit) {
        rvAdapterHelper = RvHelper()
        rvAdapterHelper.build()
    }

    fun add(items: List<ViewHolderItem>) {
        val prevSize = this.items.size
        this.items.addAll(items)
        notifyItemRangeInserted(prevSize, items.size)
    }

    fun update(newItems: List<ViewHolderItem>) {
        var diffResult: DiffUtil.DiffResult? = null
        if (onItemsSame != null && onContentSame != null) {
            val rvDiffUtil = RvAdapterDiffUtil(items, newItems, onItemsSame!!, onContentSame!!)
            diffResult = DiffUtil.calculateDiff(rvDiffUtil)
        }
        this.items.clear()
        this.items.addAll(newItems)
        diffResult?.dispatchUpdatesTo(this)
    }

}
