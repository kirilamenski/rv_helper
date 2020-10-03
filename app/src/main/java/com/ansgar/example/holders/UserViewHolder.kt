package com.ansgar.example.holders

import android.view.View
import com.ansgar.example.models.ExampleUser
import com.ansgar.rvhelper.holders.BaseViewHolder
import kotlinx.android.synthetic.main.view_holder_user.view.*

class UserViewHolder(view: View, private val listener: UserViewHolderListener):
    BaseViewHolder<ExampleUser>(view) {
    override fun bindModel(item: ExampleUser) {
        with(itemView) {
            vh_user_name_tv.text = "${item.id} - ${item.name}"
            setOnClickListener {
                listener.onClickViewHolder(item, adapterPosition)
            }
        }
    }

}
