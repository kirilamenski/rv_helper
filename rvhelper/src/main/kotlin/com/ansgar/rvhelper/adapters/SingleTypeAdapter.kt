package com.ansgar.rvhelper.adapters

import com.ansgar.rvhelper.utils.ViewHoldersUtil

class SingleTypeAdapter<VM>(viewHoldersUtil: ViewHoldersUtil) : BaseAdapter<VM>(viewHoldersUtil) {

    override fun getItemViewType(position: Int): Int = viewHoldersUtil.vhCallbacks.keyAt(0)

}