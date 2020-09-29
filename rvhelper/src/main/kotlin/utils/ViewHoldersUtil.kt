package utils

import android.util.SparseArray
import android.view.View
import androidx.annotation.LayoutRes
import holders.BaseViewHolder
import models.BaseRecyclerViewItem

class ViewHoldersUtil {

    internal val viewHolders = SparseArray<BaseRecyclerViewItem<*, *>>()

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

}