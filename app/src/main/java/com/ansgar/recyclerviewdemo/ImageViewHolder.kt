package com.ansgar.recyclerviewdemo

import android.view.View
import com.ansgar.rvhelper.holders.BaseViewHolder
import com.ansgar.rvhelper.holders.BaseViewHolderListener
import kotlinx.android.synthetic.main.view_holder_image.view.*

class ImageViewHolder(
    view: View,
    private val imageViewHolderListener: BaseViewHolderListener<MainActivity.Image>
) : BaseViewHolder<MainActivity.Image>(view), BaseViewHolderListener<MainActivity.Image> {
    override fun bindModel(item: MainActivity.Image) {
        with(itemView) {
            image_view_holder_iv.setImageResource(item.backgroundResId)
            setOnClickListener {
                onClickViewHolder(item, adapterPosition)
            }
        }
    }

    override fun onClickViewHolder(item: MainActivity.Image, position: Int) {
        itemView.setOnClickListener {
            imageViewHolderListener.onClickViewHolder(item, position)
        }
    }

}
