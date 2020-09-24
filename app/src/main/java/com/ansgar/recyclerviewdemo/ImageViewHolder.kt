package com.ansgar.recyclerviewdemo

import android.view.View
import com.ansgar.rvhelper.BaseViewHolder
import kotlinx.android.synthetic.main.view_holder_image.view.*

class ImageViewHolder(private val view: View) : BaseViewHolder<MainActivity.Image>(view) {
    override fun bindModel(item: MainActivity.Image) {
        with(view) {
            image_view_holder_iv.setImageResource(item.backgroundResId)
        }
    }

}
