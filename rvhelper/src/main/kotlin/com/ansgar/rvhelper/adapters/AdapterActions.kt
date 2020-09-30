package com.ansgar.rvhelper.adapters

import com.ansgar.rvhelper.utils.ViewHoldersUtil

internal interface AdapterActions<T> {
    fun viewHoldersUtil(build: ViewHoldersUtil.() -> Unit)

    fun addAll(items: ArrayList<T>)

    fun updateAll(items: ArrayList<T>)

    fun add(item: T, payload: Boolean = true)

    fun add(item: T, position: Int, payload: Boolean = true)

    fun delete(item: T)

    fun delete(position: Int)

    fun deleteAllLoadings()

    fun update(item: T, position: Int, payload: Boolean = true)

    fun getItemAt(position: Int): T
}