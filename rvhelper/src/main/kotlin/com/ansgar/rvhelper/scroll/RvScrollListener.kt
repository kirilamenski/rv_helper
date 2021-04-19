package com.ansgar.rvhelper.scroll

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView

open class RvScrollListener(private val onPageChanged: OnPageChanged) : RecyclerView.OnScrollListener(),
    RvAdapterObserver {

    private var page: Int = 0
    private var stopScrolling: Boolean = false

    @CallSuper
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (!recyclerView.canScrollVertically(1) && !stopScrolling) {
            page++
            onPageChanged.onPageChanged(page)
        }
    }

    @CallSuper
    override fun onNewItemEmpty() {
        stopScrolling = true
    }

    @CallSuper
    override fun onRefresh() {
        page = 0
        stopScrolling = false
    }
}