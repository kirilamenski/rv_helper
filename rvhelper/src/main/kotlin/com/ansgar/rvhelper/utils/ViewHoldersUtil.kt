package com.ansgar.rvhelper.utils

import android.util.SparseArray
import android.view.View
import androidx.annotation.LayoutRes
import com.ansgar.rvhelper.R
import com.ansgar.rvhelper.holders.BaseViewHolder
import com.ansgar.rvhelper.holders.LoadViewHolder
import com.ansgar.rvhelper.models.BaseRecyclerViewItem

class ViewHoldersUtil {

    internal val viewHolders = SparseArray<BaseRecyclerViewItem<*, *>>()
    internal var loadingLayoutResId: Int = -1

    fun getOnVhCreated(viewType: Int) =
        viewHolders[viewType]?.onViewHolderCreated

    fun <VH : BaseViewHolder<VM>, VM> getOnBindVh(viewType: Int) =
        viewHolders[viewType]?.onBindViewHolder as? (VH, VM, Int) -> Unit?

    fun <VH : BaseViewHolder<VM>, VM> create(
        @LayoutRes layoutRes: Int,
        onViewHolderCreated: (view: View) -> VH
    ) {
        viewHolders.put(layoutRes, BaseRecyclerViewItem(onViewHolderCreated))
    }

    fun <VH : BaseViewHolder<VM>, VM> create(
        @LayoutRes layoutRes: Int,
        onViewHolderCreated: (view: View) -> VH,
        onBindViewHolder: (viewHolder: VH, item: VM, position: Int) -> Unit
    ) {
        viewHolders.put(layoutRes, BaseRecyclerViewItem(onViewHolderCreated, onBindViewHolder))
    }

    fun <VH : LoadViewHolder> createLoadingViewHolder(
        @LayoutRes layoutRes: Int = R.layout.view_holder_loading,
        onViewHolderCreated: (view: View) -> VH
    ) {
        loadingLayoutResId = layoutRes
        viewHolders.put(layoutRes, BaseRecyclerViewItem<LoadViewHolder, Any>(onViewHolderCreated))
    }

}