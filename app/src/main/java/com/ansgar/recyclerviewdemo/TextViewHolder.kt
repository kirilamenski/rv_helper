package com.ansgar.recyclerviewdemo

import android.view.View
import com.ansgar.rvhelper.holders.BaseViewHolder
import kotlinx.android.synthetic.main.view_holder_text.view.*

class TextViewHolder(
    private val viewItem: View,
    private val textViewHolderListener: TextViewHolderListener
) : BaseViewHolder<MainActivity.User>(viewItem), TextViewHolderListener {

    override fun bindModel(item: MainActivity.User) {
        with(viewItem.text_view_holder_tv) {
            text = "${item.name} ${item.surName}"
            setOnClickListener {
                onTextClicked(item, adapterPosition)
            }
//            setOnLongClickListener {
//                onLongClickedViewHolder(item, adapterPosition)
//                true
//            }
        }
    }

    override fun onTextClicked(user: MainActivity.User, position: Int) {
        textViewHolderListener.onTextClicked(user, position)
    }

    override fun onClickViewHolder(item: MainActivity.User, position: Int) {
        textViewHolderListener.onClickViewHolder(item, position)
    }

    override fun onLongClickedViewHolder(item: MainActivity.User, position: Int) {
        textViewHolderListener.onLongClickedViewHolder(item, position)
    }

}