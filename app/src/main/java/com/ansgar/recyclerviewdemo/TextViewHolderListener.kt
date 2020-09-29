package com.ansgar.recyclerviewdemo

import holders.BaseViewHolderListener

interface TextViewHolderListener : BaseViewHolderListener<MainActivity.User> {
    fun onTextClicked(user: MainActivity.User, position: Int)
}