package com.ansgar.rvhelper.holders

import android.util.Log
import android.view.View

class DefaultViewHolder(view: View) : BaseViewHolder<Any>(view) {
    override fun bindModel(item: Any) {
        Log.e(
            this::class.java.simpleName,
            "You have added an object to the list that wasn't signed. Please check your signed ViewHolders"
        )
    }
}