package com.ansgar.rvhelper

import android.view.View

internal open class BaseRecyclerViewItem<VH : BaseViewHolder<VM>, VM>(
    var onViewHolderCreated: (view: View) -> VH,
    var onBindViewHolder: ((viewHolder: VH, item: VM, position: Int) -> Unit)? = null
)

