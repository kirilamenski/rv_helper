package com.ansgar.rvhelper.models

import android.view.View
import com.ansgar.rvhelper.holders.BaseViewHolder

internal open class ViewHolderCallback<VH : BaseViewHolder<*>>(
    val onViewHolderCreated: ((view: View) -> VH?)? = null
)

