package com.ansgar.recyclerviewdemo

import android.content.Intent
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ansgar.rvhelper.holders.BaseViewHolderListener
import com.ansgar.rvhelper.adapters.MultipleTypesAdapter
import com.ansgar.rvhelper.models.ViewHolderItem
import com.ansgar.rvhelper.createMultipleTypesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var page = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        createRecyclerView()
        add_new_items_bt.setOnClickListener {
            page++
            updateList(generateList(page * 25 + 1, 25))
//            updateList(list2)
        }
        open_btn.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    private fun createRecyclerView() {
        with(rv_example) {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                RecyclerView.VERTICAL,
                false
            )
            adapter = createMultipleTypesAdapter {
                addAll(generateList(0, 25))
//                addNewItems(list1)
                viewHoldersUtil {
                    create(R.layout.view_holder_header) { HeaderViewHolder(it) }
                    create(R.layout.view_holder_text) {
                        TextViewHolder(it, object : TextViewHolderListener {
                            override fun onTextClicked(user: User, position: Int) {
                                user.name = (0..1000).random().toString()
                                user.surName = (0..1000).random().toString()
                                update(user, position)
                            }

                            override fun onLongClickedViewHolder(item: User, position: Int) {
                                delete(position)
                            }
                        })
                    }
                    create(R.layout.view_holder_image) {
                        ImageViewHolder(it, object : BaseViewHolderListener<Image> {
                            override fun onClickViewHolder(item: Image, position: Int) {
                                val i = (0..100000).random()
                                item.backgroundResId = if (i % 2 == 0) {
                                    R.drawable.ic_launcher_foreground
                                } else {
                                    R.drawable.ic_launcher_background
                                }
                                notifyItemChanged(position)
                            }

                            override fun onLongClickedViewHolder(item: Image, position: Int) {
                                add(
                                    User(
                                        System.currentTimeMillis().toInt(),
                                        "New",
                                        "User"
                                    ),
                                    position
                                )
                            }
                        })
                    }
                    create(R.layout.view_holder_big_text) { BigTextViewHolder(it) }
                }
                onItemsSame = { oldItem, newItem ->
                    when {
                        oldItem is User && newItem is User -> oldItem.id == newItem.id
                        else -> false
                    }
                }
                onContentSame = { oldItem, newItem ->
                    when {
                        oldItem is User && newItem is User -> oldItem.name == newItem.name
                                && oldItem.surName == newItem.surName
                        else -> false
                    }
                }
            }
        }
    }

    /**
     * All below is just for example
     */

    private fun updateList(items: ArrayList<ViewHolderItem>) {
        with(rv_example.adapter as MultipleTypesAdapter) {
            updateAll(items)
//            add(items)
        }
    }

    private fun generateList(start: Int, size: Int): ArrayList<ViewHolderItem> {
        val array = ArrayList<ViewHolderItem>()
        (start..(start - 1 + size)).forEach {
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
                else -> array.add(User(it, "Name: $it", "SurName: $it"))
            }
        }
        return array
    }

    // Example for DiffUtil
    private val list1 = ArrayList<ViewHolderItem>().apply {
        add(User(0, "Name: 0", "SurName: 0"))
        add(User(1, "Name: 1", "SurName: 1"))
        add(User(2, "Name: 2", "SurName: 2"))
        add(User(3, "Name: 3", "SurName: 3"))
        add(User(4, "Name: 4", "SurName: 4"))
    }

    private val list2 = ArrayList<ViewHolderItem>().apply {
        add(User(0, "Name: 0", "SurName: 0"))
        add(User(1, "Name: 1", "SurName: 1"))
        add(User(6, "Name: 6", "SurName: 6"))
        add(User(3, "Name: 3", "SurName: 3"))
        add(User(4, "Name: 4", "SurName: 4"))
    }

    data class User(
        var id: Int,
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