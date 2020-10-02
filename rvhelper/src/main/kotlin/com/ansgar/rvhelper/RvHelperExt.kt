package com.ansgar.rvhelper

import com.ansgar.rvhelper.adapters.MultipleTypesAdapter
import com.ansgar.rvhelper.adapters.SingleTypeAdapter
import com.ansgar.rvhelper.utils.ViewHoldersUtil

/**
 * Extension class that provide the ability to create adapter for Recycler View
 * example: RecyclerView.adapter = [createAdapter]/[createSingleTypeAdapter]
 */
/**
 * [createSingleTypeAdapter] provide the ability to create adapter with only one view type.
 * The difference between [createMultipleTypesAdapter] is that it contain generic [VM] (View Model) which define
 * type of [ArrayList].
 * Create DSL to create adapter.
 * @param build [SingleTypeAdapter] instance.
 * @return [SingleTypeAdapter] adapter for only one view holder type.
 */
inline fun <VM> ViewHoldersUtil.createSingleTypeAdapter(
    build: SingleTypeAdapter<VM>.() -> Unit
): SingleTypeAdapter<VM> {
    val singleTypeAdapter = SingleTypeAdapter<VM>(this)
    singleTypeAdapter.build()
    return singleTypeAdapter
}

/**
 * [createMultipleTypesAdapter] create an adapter for many view holders type.
 * This adapter use [ViewHolderItem] that contain layoutResId as basic view model class for
 * all types which should be display in [RecyclerView]. To use it you should extend your class
 * with [ViewHolderItem].
 * @param build [MultipleTypesAdapter] instance.
 * @return [MultipleTypesAdapter] adapter for multiple view holders types.
 */
inline fun ViewHoldersUtil.createMultipleTypesAdapter(
    build: MultipleTypesAdapter.() -> Unit
): MultipleTypesAdapter {
    val rvAdapter = MultipleTypesAdapter(this)
    rvAdapter.build()
    return rvAdapter
}

/**
 * Create an instance of [ViewHoldersUtil] a class that contains view holders and listeners.
 * @param build instance of [ViewHoldersUtil]
 */
inline fun viewHoldersUtil(build: ViewHoldersUtil.() -> Unit): ViewHoldersUtil {
    val viewHoldersUtil = ViewHoldersUtil()
    viewHoldersUtil.build()
    return viewHoldersUtil
}

//inline fun <T : MultipleTypesAdapter> createMultipleTypesAdapter(
//    clazz: Class<T>,
//    build: T.() -> Unit
//): T {
//    val adapter = clazz.newInstance()
//    adapter.build()
//    return adapter
//}