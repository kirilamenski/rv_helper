package com.ansgar.rvhelper.holders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<VM, B: ViewBinding>(binding: B) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bindModel(item: VM)

}
