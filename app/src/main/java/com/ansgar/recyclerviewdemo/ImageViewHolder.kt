package com.ansgar.recyclerviewdemo

import android.view.View
import holders.BaseViewHolder
import holders.BaseViewHolderListener
import kotlinx.android.synthetic.main.view_holder_image.view.*

class ImageViewHolder(
    view: View,
    private val imageViewHolderListener: BaseViewHolderListener<MainActivity.Image>
) : BaseViewHolder<MainActivity.Image>(view) {

    override fun bindModel(item: MainActivity.Image) {
        with(itemView) {
            image_view_holder_iv.setImageResource(item.backgroundResId)
            setOnClickListener {
                imageViewHolderListener.onClickViewHolder(item, adapterPosition)
            }
            setOnLongClickListener {
                imageViewHolderListener.onLongClickedViewHolder(item, adapterPosition)
                true
            }
        }
    }

}
