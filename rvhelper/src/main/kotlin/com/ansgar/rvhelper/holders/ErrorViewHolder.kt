package com.ansgar.rvhelper.holders

import android.util.Log
import android.view.View
import com.ansgar.rvhelper.databinding.ViewHolderErrorBinding

class ErrorViewHolder(
    binding: ViewHolderErrorBinding
) : BaseViewHolder<Any, ViewHolderErrorBinding>(binding) {
    override fun bindModel(item: Any) {
        Log.e(
            this::class.java.simpleName,
            "You have added an object to the list that wasn't signed. Please check your signed ViewHolders"
        )
    }
}