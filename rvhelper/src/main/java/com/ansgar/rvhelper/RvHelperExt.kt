package com.ansgar.rvhelper

inline fun createAdapter(build: RvAdapter.() -> Unit): RvAdapter {
    val rvAdapter = RvAdapter()
    rvAdapter.build()
    return rvAdapter
}

inline fun <T: RvAdapter> createAdapter(clazz: Class<T>, build: T.() -> Unit): T {
    val adapter = clazz.newInstance()
    adapter.build()
    return adapter
}