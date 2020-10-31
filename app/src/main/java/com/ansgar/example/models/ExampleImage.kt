package com.ansgar.example.models

import androidx.annotation.DrawableRes
import com.ansgar.rvhelper.models.ViewHolderItem

data class ExampleImage(
    @DrawableRes val drawableRes: Int,
    val imageTitle: String
) : ViewHolderItem()