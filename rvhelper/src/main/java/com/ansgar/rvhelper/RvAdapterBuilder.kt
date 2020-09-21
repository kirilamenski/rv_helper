package com.ansgar.rvhelper

import android.util.SparseArray
import android.view.View
import androidx.annotation.LayoutRes

class RvAdapterBuilder {

    private val viewHolders = SparseArray<BaseRecyclerViewItem<*, *>>()

    fun getOnVhCreated(viewType: Int) =
        viewHolders[viewType].onViewHolderCreated

    fun <VH : BaseViewHolder, VM> getOnBindVh(viewType: Int) =
        viewHolders[viewType].onBindViewHolder as (VH, VM) -> Unit

    fun <VH : BaseViewHolder, VM> register(
        @LayoutRes layoutRes: Int,
        onViewHolderCreated: (view: View) -> VH,
        onBindViewHolder: (viewHolder: VH, item: VM) -> Unit
    ) {
        viewHolders.put(
            layoutRes,
            BaseRecyclerViewItem(layoutRes, onViewHolderCreated, onBindViewHolder)
        )
    }

}