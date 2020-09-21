package com.ansgar.recyclerviewdemo

import android.view.View
import com.ansgar.rvhelper.BaseViewHolder
import kotlinx.android.synthetic.main.view_holder_text.view.*

class TextViewHolder(private val viewItem: View) : BaseViewHolder(viewItem) {
    override fun bind(item: Any) {
        item as MainActivity.User
        viewItem.text_view_holder_tv.text = "${item.name} ${item.surName}"
    }

}