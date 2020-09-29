package com.ansgar.recyclerviewdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ansgar.rvhelper.createSingleTypeAdapter
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        createList()
    }

    private fun createList() {
        with(second_activity_rv) {
            layoutManager = LinearLayoutManager(
                this@SecondActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = createSingleTypeAdapter<MainActivity.User> {
                addAll(generateList(0, 20))
                viewHoldersUtil {
                    create(R.layout.view_holder_text) {
                        TextViewHolder(it, object : TextViewHolderListener {
                            override fun onTextClicked(user: MainActivity.User, position: Int) {
                                user.name = "Hey "
                                user.surName = "${System.currentTimeMillis()}"
                                notifyItemChanged(position)
                            }

                            override fun onLongClickedViewHolder(
                                item: MainActivity.User,
                                position: Int
                            ) {
                                delete(position)
                            }
                        })
                    }
                }
            }
        }
    }

    private fun generateList(start: Int, size: Int): ArrayList<MainActivity.User> {
        val array = ArrayList<MainActivity.User>()
        (start..(start - 1 + size)).forEach {
            array.add(MainActivity.User(it, "Name: $it", "SurName: $it"))
        }
        return array
    }

}