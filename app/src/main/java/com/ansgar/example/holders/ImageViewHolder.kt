package com.ansgar.example.holders

import android.view.View
import com.ansgar.example.models.ExampleImage
import com.ansgar.rvhelper.holders.BaseViewHolder
import kotlinx.android.synthetic.main.view_holder_image.view.*

class ImageViewHolder(view: View): BaseViewHolder<ExampleImage>(view) {
    override fun bindModel(item: ExampleImage) {
        with(itemView) {
            vh_image_iv.setBackgroundResource(item.drawableRes)
            vh_image_title_tv.text = item.imageTitle
        }
    }

}
