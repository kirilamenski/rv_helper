package adapters

import utils.ViewHoldersUtil

internal interface AdapterActions<T> {
    fun viewHoldersUtil(build: ViewHoldersUtil.() -> Unit)

    fun addAll(items: List<T>)

    fun updateAll(items: List<T>)

    fun add(item: T, payload: Boolean = true)

    fun add(item: T, position: Int, payload: Boolean = true)

    fun delete(item: T)

    fun delete(position: Int)

    fun update(item: T, position: Int, payload: Boolean = true)

    fun getItemAt(position: Int): T
}