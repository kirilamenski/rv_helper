package com.ansgar.recyclerviewdemo

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ansgar.rvhelper.ViewHolderItem
import com.ansgar.rvhelper.createAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_holder_big_text.view.*

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
                register<TextViewHolder, User>(
                    R.layout.view_holder_text,
                    { TextViewHolder(it) },
                    { viewHolder, item -> }
                )
                register<ImageViewHolder, Image>(
                    R.layout.view_holder_image,
                    { ImageViewHolder(it) },
                    { viewHolder, item -> }
                )
                register<BigTextViewHolder, WebImage>(
                    R.layout.view_holder_big_text,
                    { BigTextViewHolder(it) },
                    { viewHolder, item ->
                        with(viewHolder.itemView) {
                            Glide.with(context)
                                .load(item.url)
                                .centerCrop()
                                .into(image_iv)
                            big_text_tv.text = item.text
                        }
                    }
                )
            }) {
                items.addAll(generateList())
            }
        }
    }

    private fun generateList(): ArrayList<ViewHolderItem> {
        val array = ArrayList<ViewHolderItem>()
        (0..100).forEach {
            when {
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

}