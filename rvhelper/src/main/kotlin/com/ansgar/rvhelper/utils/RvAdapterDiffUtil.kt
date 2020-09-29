package com.ansgar.rvhelper.utils

import androidx.recyclerview.widget.DiffUtil

class RvAdapterDiffUtil<T>(
    private val oldItems: List<T>,
    private val newItems: List<T>,
    private val onItemsSame: ((oldItem: T, newItem: T) -> Boolean),
    private val onContentSame: ((oldItem: T, newItem: T) -> Boolean),
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