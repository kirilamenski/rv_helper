package com.ansgar.example.models

import com.ansgar.rvhelper.models.ViewHolderItem

data class ExampleUser(
    var id: Int,
    var name: String,
    var surName: String
): ViewHolderItem()
