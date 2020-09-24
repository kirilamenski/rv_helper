package com.ansgar.recyclerviewdemo

import android.view.View
import com.ansgar.rvhelper.BaseViewHolder
import kotlinx.android.synthetic.main.view_holder_text.view.*

class TextViewHolder(private val viewItem: View) : BaseViewHolder<MainActivity.User>(viewItem) {
    override fun bindModel(item: MainActivity.User) {
        with(viewItem) {
            text_view_holder_tv.text = "${item.name} ${item.surName}"
        }
    }

}