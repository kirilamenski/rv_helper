package com.ansgar.recyclerviewdemo

import android.os.Bundle
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ansgar.rvhelper.BaseRecyclerViewItem
import com.ansgar.rvhelper.ViewHolderItem
import com.ansgar.rvhelper.createAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        createRecyclerView()
    }

    private fun createRecyclerView() {
        with(rv_example) {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                RecyclerView.VERTICAL,
                false
            )
            adapter = createAdapter({
                registerViewHolder(R.layout.view_holder_text) {
                    TextViewHolder(it)
                }
                registerViewHolder(R.layout.view_holder_image) {
                    ImageViewHolder(it)
                }
            }) {
                items.addAll(generateList())
            }
        }
    }

    private fun generateList(): ArrayList<ViewHolderItem> {
        val array = ArrayList<ViewHolderItem>()
        (0..100).forEach {
            if (it % 5 == 0) {
                array.add(Image(R.drawable.ic_launcher_background))
            } else {
                array.add(User("Name: $it", "SurName: $it"))
            }
        }
        return array
    }

    data class User(
        var name: String,
        var surName: String
    ) : ViewHolderItem(R.layout.view_holder_text)

    data class Image(
        @DrawableRes
        var backgroundResId: Int
    ) : ViewHolderItem(R.layout.view_holder_image)

}