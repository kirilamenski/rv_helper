package com.ansgar.rvhelper

inline fun createAdapter(
    build: RvAdapterBuilder.() -> Unit,
    rvAdapterBuild: RvAdapter.() -> Unit
): RvAdapter {
    val rvBuilder = RvAdapterBuilder()
    rvBuilder.build()
    val rvAdapter = RvAdapter(rvBuilder)
    rvAdapter.rvAdapterBuild()
    return rvAdapter
}