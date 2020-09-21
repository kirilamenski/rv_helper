package com.ansgar.rvhelper

inline fun createAdapter(build: RvAdapter.() -> Unit): RvAdapter {
    val rvAdapter = RvAdapter()
    rvAdapter.build()
    return rvAdapter
}