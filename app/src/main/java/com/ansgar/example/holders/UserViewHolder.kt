package com.ansgar.example.holders

import com.ansgar.example.models.ExampleUser
import com.ansgar.rvhelper.holders.BaseViewHolder
import com.ansgar.rvhelperexample.databinding.ViewHolderUserBinding

//class UserViewHolder(view: View, private val listener: UserViewHolderListener):
class UserViewHolder(private val binding: ViewHolderUserBinding) :
    BaseViewHolder<ExampleUser, ViewHolderUserBinding>(binding) {
    override fun bindModel(item: ExampleUser) {
        with(binding) {
            vhUserNameTv.text = "${item.id} - ${item.name}"
//            setOnClickListener {
//                listener.onClickViewHolder(item, adapterPosition)
//            }
        }
    }

}
