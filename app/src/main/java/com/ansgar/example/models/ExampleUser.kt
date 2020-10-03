package com.ansgar.example.models

import com.ansgar.rvhelper.models.ViewHolderItem
import com.ansgar.rvhelperexample.R

data class ExampleUser(
    var id: Int,
    var name: String,
    var surName: String
): ViewHolderItem(R.layout.view_holder_user)
