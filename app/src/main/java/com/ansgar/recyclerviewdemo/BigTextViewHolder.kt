package com.ansgar.recyclerviewdemo

import android.view.View
import com.ansgar.rvhelper.BaseViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_holder_big_text.view.*

class BigTextViewHolder(private val view: View) : BaseViewHolder<MainActivity.WebImage>(view) {
    override fun bindModel(item: MainActivity.WebImage) {
        with(view) {
            Glide.with(context)
                .load(item.url)
                .centerCrop()
                .into(image_iv)
        }
    }
}
