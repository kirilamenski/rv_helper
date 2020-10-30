package com.ansgar.rvhelper.adapters

import com.ansgar.rvhelper.models.DefaultLoading
import com.ansgar.rvhelper.models.ViewHolderItem
import com.ansgar.rvhelper.utils.ViewHoldersUtil

/**
 * Adapter use to create multiple [RecyclerView] with multiple view types.
 * [ViewHolderItem] is the basic class which contain layoutRes that [RecyclerView.getItemViewType]
 * use to create many different [ViewHolder].
 */
open class MultipleTypesAdapter(viewHoldersUtil: ViewHoldersUtil) :
    BaseAdapter<ViewHolderItem>(viewHoldersUtil) {

    override fun getItemViewType(position: Int): Int =
        viewHoldersUtil.getLayoutRes(items[position]::class.java.simpleName)

    override fun addAll(items: ArrayList<ViewHolderItem>) {
        if (viewHoldersUtil.loadingLayoutResId != -1) {
            deleteLoadingViewHolder()
            if (items.isNotEmpty()) items.add(DefaultLoading())
        }
        super.addAll(items)
    }

    override fun deleteLoadingViewHolder() {
        items.lastOrNull()?.let {
            if (it is DefaultLoading) delete(items.lastIndex)
        }
    }

}
