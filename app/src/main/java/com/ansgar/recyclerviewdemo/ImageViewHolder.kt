package com.ansgar.recyclerviewdemo

import android.view.View
import com.ansgar.rvhelper.BaseViewHolder
import kotlinx.android.synthetic.main.view_holder_image.view.*

class ImageViewHolder(private val view: View) : BaseViewHolder<MainActivity.Image>(view) {
    override fun bindModel(item: MainActivity.Image) {
        with(view) {
            val rnd = (0..10).random()
            image_view_holder_iv.setImageResource(
                when {
                    rnd % 2 == 0 -> R.drawable.ic_launcher_background
                    else -> R.drawable.ic_launcher_foreground
                }
            )
        }
    }

}
