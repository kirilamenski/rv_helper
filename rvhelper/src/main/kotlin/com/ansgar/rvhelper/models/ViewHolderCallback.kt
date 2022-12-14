package com.ansgar.rvhelper.models

import android.view.ViewGroup
import com.ansgar.rvhelper.holders.BaseViewHolder

internal open class ViewHolderCallback<VH : BaseViewHolder<*, *>>(
    val onViewHolderCreated: ((parent: ViewGroup) -> VH?)? = null
)

