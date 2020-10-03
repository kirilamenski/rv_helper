package com.ansgar.example.models

import androidx.annotation.DrawableRes
import com.ansgar.rvhelper.models.ViewHolderItem
import com.ansgar.rvhelperexample.R

data class ExampleImage(
    @DrawableRes val drawableRes: Int,
    val imageTitle: String
) : ViewHolderItem(R.layout.view_holder_image)