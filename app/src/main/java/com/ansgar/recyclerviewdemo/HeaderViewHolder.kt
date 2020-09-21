package com.ansgar.recyclerviewdemo

import android.view.View
import com.ansgar.rvhelper.BaseViewHolder
import kotlinx.android.synthetic.main.view_holder_header.view.*

class HeaderViewHolder(private val view: View): BaseViewHolder<MainActivity.HeaderModel>(view) {
    override fun bind(item: MainActivity.HeaderModel) {
        with(view) {
            header_holder_tv.text = item.text
        }
    }

}
