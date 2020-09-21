package com.ansgar.rvhelper

import android.util.SparseArray
import android.view.View
import androidx.annotation.LayoutRes

class RvAdapterBuilder {

    private val viewHolders = SparseArray<BaseRecyclerViewItem<*>>()

    fun getOnVhCreated(viewType: Int) =
        viewHolders[viewType].onViewHolderCreated

    fun <VH : BaseViewHolder> getOnBindVh(viewType: Int) =
        viewHolders[viewType].onBindViewHolder as (VH, Int, Any) -> Unit

    fun <T : BaseViewHolder> register(
        @LayoutRes layoutRes: Int,
        onViewHolderCreated: (view: View) -> T,
        onBindViewHolder: (viewHolder: T, position: Int, item: Any) -> Unit
    ) {
        viewHolders.put(
            layoutRes,
            BaseRecyclerViewItem(layoutRes, onViewHolderCreated, onBindViewHolder)
        )
    }

}