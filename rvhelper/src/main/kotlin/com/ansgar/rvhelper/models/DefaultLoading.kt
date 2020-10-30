package com.ansgar.rvhelper.models

import androidx.annotation.LayoutRes
import com.ansgar.rvhelper.R

open class DefaultLoading(@LayoutRes layoutRes: Int = R.layout.view_holder_default_loading) :
    ViewHolderItem(layoutRes)