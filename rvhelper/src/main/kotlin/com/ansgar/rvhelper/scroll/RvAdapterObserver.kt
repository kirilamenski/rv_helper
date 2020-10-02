package com.ansgar.rvhelper.scroll

interface RvAdapterObserver {
    fun onNewItemEmpty()

    fun onRefresh()
}