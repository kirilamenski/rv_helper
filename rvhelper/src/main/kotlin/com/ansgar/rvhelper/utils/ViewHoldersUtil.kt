package com.ansgar.rvhelper.utils

import android.util.ArrayMap
import android.util.SparseArray
import android.view.View
import androidx.annotation.LayoutRes
import com.ansgar.rvhelper.R
import com.ansgar.rvhelper.holders.BaseViewHolder
import com.ansgar.rvhelper.holders.DefaultLoadingViewHolder
import com.ansgar.rvhelper.models.BaseRecyclerViewItem
import com.ansgar.rvhelper.models.DefaultLoading

class ViewHoldersUtil {

    internal val viewHolders = SparseArray<BaseRecyclerViewItem<*, *>>()
    internal val experimentalViewHolders = ArrayMap<String, Int>()
    internal var loadingLayoutResId: Int = -1

    fun getOnVhCreated(viewType: Int) =
        viewHolders[viewType]?.onViewHolderCreated

    //    fun getLayoutRes(simpleClassName: String) = experimentalViewHolders.firstOrNull {
//        it.className == simpleClassName
//    }?.layoutRes ?: R.layout.view_holder_error
    fun getLayoutRes(simpleClassName: String) =
        experimentalViewHolders[simpleClassName] ?: R.layout.view_holder_error

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
        clazz: Class<VM>,
        onViewHolderCreated: (view: View) -> VH,
    ) {
//        experimentalViewHolders.add(
//            BaseRecyclerViewItem(
//                onViewHolderCreated,
//                null,
//                clazz.simpleName,
//                layoutRes
//            )
//        )
        experimentalViewHolders[clazz.simpleName] = layoutRes
        create(layoutRes, onViewHolderCreated)
    }

    fun <VH : BaseViewHolder<VM>, VM> create(
        @LayoutRes layoutRes: Int,
        onViewHolderCreated: (view: View) -> VH,
        onBindViewHolder: (viewHolder: VH, item: VM, position: Int) -> Unit
    ) {
        viewHolders.put(layoutRes, BaseRecyclerViewItem(onViewHolderCreated, onBindViewHolder))
    }

    fun <VH : DefaultLoadingViewHolder> createLoadingViewHolder(
        onViewHolderCreated: (view: View) -> VH
    ) {
        createLoadingViewHolder(R.layout.view_holder_default_loading, onViewHolderCreated)
    }

    fun <VH : DefaultLoadingViewHolder> createLoadingViewHolder(
        @LayoutRes layoutRes: Int,
        onViewHolderCreated: (view: View) -> VH
    ) {
        loadingLayoutResId = layoutRes
//        experimentalViewHolders.add(
//            BaseRecyclerViewItem(
//                onViewHolderCreated,
//                null,
//                Any::class.java.simpleName,
//                layoutRes
//            )
//        )
//        viewHolders.put(
//            layoutRes,
//            BaseRecyclerViewItem<DefaultLoadingViewHolder, Any>(onViewHolderCreated)
//        )
        create(layoutRes, DefaultLoading::class.java, onViewHolderCreated)
    }

}