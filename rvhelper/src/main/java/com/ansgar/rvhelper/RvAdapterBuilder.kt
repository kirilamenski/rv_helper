package com.ansgar.rvhelper

import android.util.SparseArray
import android.view.View
import androidx.annotation.LayoutRes

class RvAdapterBuilder {

    private val viewHolders = SparseArray<BaseRecyclerViewItem<*>>()

    fun getOnVhCreatedListener(viewType: Int) =
        viewHolders[viewType].onViewHolderCreated

    fun registerViewHolder(
        @LayoutRes layoutRes: Int,
        onViewHolderCreated: (view: View) -> BaseViewHolder
    ) {
        val baseItem = BaseRecyclerViewItem(
            layoutRes,
            onViewHolderCreated
        )
        viewHolders.put(layoutRes, baseItem)
    }

}