package com.ansgar.example.holders

import com.ansgar.example.models.ExampleImage
import com.ansgar.rvhelper.holders.BaseViewHolder
import com.ansgar.rvhelperexample.databinding.ViewHolderImageBinding

class ImageViewHolder(private val binding: ViewHolderImageBinding) :
    BaseViewHolder<ExampleImage, ViewHolderImageBinding>(binding) {
    override fun bindModel(item: ExampleImage) {
        with(binding) {
            vhImageIv.setBackgroundResource(item.drawableRes)
            vhImageTitleTv.text = item.imageTitle
        }
    }

}
