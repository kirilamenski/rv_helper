package com.ansgar.rvhelper

import android.view.View
import androidx.annotation.LayoutRes

open class BaseRecyclerViewItem<VH : BaseViewHolder>(
    @LayoutRes val layoutRes: Int,
    var onViewHolderCreated: (view: View) -> VH,
    var onBindViewHolder: ((viewHolder: VH, position: Int, item: Any) -> Unit)? = null
)

