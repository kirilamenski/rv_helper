package com.ansgar.rvhelper.utils

import android.util.ArrayMap
import android.util.SparseArray
import android.view.View
import androidx.annotation.LayoutRes
import com.ansgar.rvhelper.R
import com.ansgar.rvhelper.holders.BaseViewHolder
import com.ansgar.rvhelper.holders.DefaultLoadingViewHolder
import com.ansgar.rvhelper.models.ViewHolderCallback
import com.ansgar.rvhelper.models.DefaultLoading

class ViewHoldersUtil {

    internal val vhCallbacks = SparseArray<ViewHolderCallback<*>>()
    internal val vhLayouts = ArrayMap<String, Int>()
    internal var loadingLayoutResId: Int = -1

    fun getOnVhCreated(viewType: Int) =
        vhCallbacks[viewType]?.onViewHolderCreated

    fun getLayoutRes(simpleClassName: String) =
        vhLayouts[simpleClassName] ?: R.layout.view_holder_error

    fun <VH : BaseViewHolder<VM>, VM> create(
        @LayoutRes layoutRes: Int,
        onViewHolderCreated: (view: View) -> VH
    ) {
        vhCallbacks.put(layoutRes, ViewHolderCallback(onViewHolderCreated))
    }

    fun <VH : BaseViewHolder<VM>, VM> create(
        @LayoutRes layoutRes: Int,
        clazz: Class<VM>,
        onViewHolderCreated: (view: View) -> VH,
    ) {
        vhLayouts[clazz.simpleName] = layoutRes
        create(layoutRes, onViewHolderCreated)
    }

    fun createLoadingViewHolder(onViewHolderCreated: (view: View) -> DefaultLoadingViewHolder) {
        createLoadingViewHolder(R.layout.view_holder_default_loading, onViewHolderCreated)
    }

    fun <VH : DefaultLoadingViewHolder> createLoadingViewHolder(
        @LayoutRes layoutRes: Int,
        onViewHolderCreated: (view: View) -> VH
    ) {
        loadingLayoutResId = layoutRes
        create(layoutRes, DefaultLoading::class.java, onViewHolderCreated)
    }

}