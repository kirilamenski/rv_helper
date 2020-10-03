package com.ansgar.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ansgar.example.holders.UserViewHolder
import com.ansgar.example.holders.UserViewHolderListener
import com.ansgar.example.models.ExampleUser
import com.ansgar.rvhelper.adapters.SingleTypeAdapter
import com.ansgar.rvhelper.createSingleTypeAdapter
import com.ansgar.rvhelper.viewHoldersUtil
import com.ansgar.rvhelperexample.R
import kotlinx.android.synthetic.main.example_activity.*

class ExampleActivity: AppCompatActivity() {

    // viewHoldersUtil is a helper class that contains your view holders and where you should define the view holders
    private val viewHoldersUtil = viewHoldersUtil {
        // you need to call create function and pass @LayoutRes layoutResId and you view holder class
        create(R.layout.view_holder_user) {
            UserViewHolder(it, object : UserViewHolderListener {
                override fun onClickViewHolder(item: ExampleUser, position: Int) {
                    item.name = "User name changed"
                    rvAdapter.update(item, position)
                }
            })
        }
    }
    private lateinit var rvAdapter: SingleTypeAdapter<ExampleUser>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_activity)
        createRecyclerView()
    }

    private fun createRecyclerView() {
        with(example_Rv) {
            layoutManager = LinearLayoutManager(
                this@ExampleActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            rvAdapter = viewHoldersUtil.createSingleTypeAdapter { // <- returns adapter for your list
                // to pass values to a list
                addAll(getFakeUsers())
            }
            adapter = rvAdapter
        }
    }

    private fun getFakeUsers(): ArrayList<ExampleUser> {
        val arrayList = ArrayList<ExampleUser>()
        for (i in 1..50) {
            arrayList.add(ExampleUser(i, "$i User name", "User last name"))
        }
        return arrayList
    }

}