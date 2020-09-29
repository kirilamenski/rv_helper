import adapters.MultipleTypesAdapter
import adapters.SingleTypeAdapter

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
inline fun <VM> createSingleTypeAdapter(
    build: SingleTypeAdapter<VM>.() -> Unit
): SingleTypeAdapter<VM> {
    val singleTypeAdapter = SingleTypeAdapter<VM>()
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
inline fun createMultipleTypesAdapter(build: MultipleTypesAdapter.() -> Unit): MultipleTypesAdapter {
    val rvAdapter = MultipleTypesAdapter()
    rvAdapter.build()
    return rvAdapter
}

inline fun <T : MultipleTypesAdapter> createMultipleTypesAdapter(clazz: Class<T>, build: T.() -> Unit): T {
    val adapter = clazz.newInstance()
    adapter.build()
    return adapter
}