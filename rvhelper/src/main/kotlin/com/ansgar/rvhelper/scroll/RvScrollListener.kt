package com.ansgar.rvhelper.scroll

import androidx.recyclerview.widget.RecyclerView

class RvScrollListener(private val onPageChanged: OnPageChanged) : RecyclerView.OnScrollListener(),
    RvAdapterObserver {

    private var page: Int = 0
    private var stopScrolling: Boolean = false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (!recyclerView.canScrollVertically(1) && !stopScrolling) {
            page++
            onPageChanged.onPageChanged(page)
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
    }

    override fun onNewItemEmpty() {
        stopScrolling = true
    }

    override fun onRefresh() {
        page = 0
        stopScrolling = false
    }
}