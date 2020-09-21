package com.ansgar.rvhelper

import android.view.View
import androidx.annotation.LayoutRes

open class BaseRecyclerViewItem<VH : BaseViewHolder, VM>(
    @LayoutRes val layoutRes: Int,
    var onViewHolderCreated: (view: View) -> VH,
    var onBindViewHolder: ((viewHolder: VH, item: VM) -> Unit)? = null
)

