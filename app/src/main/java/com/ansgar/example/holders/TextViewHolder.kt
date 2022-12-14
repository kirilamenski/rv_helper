package com.ansgar.example.holders

import com.ansgar.example.models.ExampleText
import com.ansgar.rvhelper.holders.BaseViewHolder
import com.ansgar.rvhelperexample.databinding.ViewHolderTextBinding

class TextViewHolder(private val binding: ViewHolderTextBinding) :
    BaseViewHolder<ExampleText, ViewHolderTextBinding>(binding) {
    override fun bindModel(item: ExampleText) {
        with(binding) {
            vhTextTv.text = item.text
        }
    }
}