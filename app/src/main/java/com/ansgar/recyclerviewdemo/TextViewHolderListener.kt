package com.ansgar.recyclerviewdemo

import com.ansgar.rvhelper.holders.BaseViewHolderListener

interface TextViewHolderListener : BaseViewHolderListener<MainActivity.User> {
    fun onTextClicked(user: MainActivity.User, position: Int)
}