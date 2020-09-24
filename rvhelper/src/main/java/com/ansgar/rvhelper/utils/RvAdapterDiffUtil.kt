package com.ansgar.rvhelper

import androidx.recyclerview.widget.DiffUtil
import com.ansgar.rvhelper.models.ViewHolderItem

class RvAdapterDiffUtil(
    private val oldItems: List<ViewHolderItem>,
    private val newItems: List<ViewHolderItem>,
    private val onItemsSame: ((oldItem: ViewHolderItem, newItem: ViewHolderItem) -> Boolean),
    private val onContentSame: ((oldItem: ViewHolderItem, newItem: ViewHolderItem) -> Boolean),
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ) = onItemsSame.invoke(oldItems[oldItemPosition], newItems[newItemPosition])

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ) = onContentSame.invoke(oldItems[oldItemPosition], newItems[newItemPosition])
}