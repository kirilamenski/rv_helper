package com.ansgar.recyclerviewdemo

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
                register(R.layout.view_holder_header) { HeaderViewHolder(it) }
                register(R.layout.view_holder_text) { TextViewHolder(it) }
                register(R.layout.view_holder_image) { ImageViewHolder(it) }
                register(R.layout.view_holder_big_text) { BigTextViewHolder(it) }
            }) {
                items.addAll(generateList())
            }
        }
    }


    /**
     * All below is just for example
     */
    private fun generateList(): ArrayList<ViewHolderItem> {
        val array = ArrayList<ViewHolderItem>()
        (0..100).forEach {
            when {
                it % 10 == 0 -> array.add(HeaderModel("$it - Header Label"))
                it % 5 == 0 -> array.add(Image(R.drawable.ic_launcher_background))
                it % 3 == 0 -> array.add(
                    WebImage(
                        "https://cdn.mos.cms.futurecdn.net/7yeCJ65MhGg2egTyeAGPfN-650-80.jpg.webp",
                        "$it - Of all the rocky, inner worlds of the solar system, Venus is the most challenging to explore. " +
                                "With surface temperatures reaching a bewildering 867 degrees Fahrenheit (464 degrees Celsius), " +
                                "even the most hardened landers can't survive for long. But a new idea, called the Calypso Venus Scout, " +
                                "calls for a bold new mission design: a science probe dangling 20 miles (32 kilometers) below a cloud-borne balloon. "
                    )
                )
                else -> array.add(User("Name: $it", "SurName: $it"))
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

    data class WebImage(
        var url: String,
        var text: String
    ) : ViewHolderItem(R.layout.view_holder_big_text)

    data class HeaderModel(var text: String) : ViewHolderItem(R.layout.view_holder_header)

}