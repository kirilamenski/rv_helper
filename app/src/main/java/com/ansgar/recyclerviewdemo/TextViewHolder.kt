package com.ansgar.recyclerviewdemo

import android.view.View
import com.ansgar.rvhelper.holders.BaseViewHolder
import kotlinx.android.synthetic.main.view_holder_text.view.*

class TextViewHolder(
    private val viewItem: View,
    private val textViewHolderListener: TextViewHolderListener
) : BaseViewHolder<MainActivity.User>(viewItem) {

    override fun bindModel(item: MainActivity.User) {
        with(viewItem.text_view_holder_tv) {
            text = "${item.name} ${item.surName}"
            setOnClickListener {
                textViewHolderListener.onTextClicked(item, adapterPosition)
            }
            setOnLongClickListener {
                textViewHolderListener.onLongClickedViewHolder(item, adapterPosition)
                true
            }
        }
    }

}