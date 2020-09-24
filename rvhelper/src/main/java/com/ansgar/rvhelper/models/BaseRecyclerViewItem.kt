package com.ansgar.rvhelper.models

import android.view.View
import com.ansgar.rvhelper.holders.BaseViewHolder

internal open class BaseRecyclerViewItem<VH : BaseViewHolder<VM>, VM>(
    var onViewHolderCreated: (view: View) -> VH?,
    var onBindViewHolder: ((viewHolder: VH, item: VM, position: Int) -> Unit)? = null
)

