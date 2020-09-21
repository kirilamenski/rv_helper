package com.ansgar.rvhelper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class RvAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private lateinit var rvAdapterHelper: RvHelper
    val items = ArrayList<ViewHolderItem>()

    override fun getItemViewType(position: Int): Int = items[position].layoutRes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> =
        rvAdapterHelper.getOnVhCreated(viewType)
            .invoke(
                LayoutInflater.from(parent.context).inflate(
                    viewType,
                    parent,
                    false
                )
            )

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
//        val item = items[position]
//        val onBind =
//            rvAdapterHelper.getOnBindVh<BaseViewHolder<ViewHolderItem>, ViewHolderItem>(getItemViewType(position))
//        onBind.invoke(holder as BaseViewHolder<ViewHolderItem>, item)
        (holder as BaseViewHolder<ViewHolderItem>).bindModel(items[position])
    }

    override fun getItemCount(): Int = items.size


    fun setHelper(build: RvHelper.() -> Unit) {
        rvAdapterHelper = RvHelper()
        rvAdapterHelper.build()
    }

}