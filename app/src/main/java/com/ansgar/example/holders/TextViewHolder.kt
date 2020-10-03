package com.ansgar.example.holders

import android.view.View
import com.ansgar.example.models.ExampleText
import com.ansgar.rvhelper.holders.BaseViewHolder
import kotlinx.android.synthetic.main.view_holder_text.view.*

class TextViewHolder(view: View) : BaseViewHolder<ExampleText>(view) {
    override fun bindModel(item: ExampleText) {
        with(itemView) {
            vh_text_tv.text = item.text
        }
    }
}