package com.ansgar.example

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ansgar.example.holders.ImageViewHolder
import com.ansgar.example.holders.TextViewHolder
import com.ansgar.example.holders.UserViewHolder
import com.ansgar.example.holders.UserViewHolderListener
import com.ansgar.example.models.ExampleImage
import com.ansgar.example.models.ExampleText
import com.ansgar.example.models.ExampleUser
import com.ansgar.rvhelper.adapters.MultipleTypesAdapter
import com.ansgar.rvhelper.adapters.SingleTypeAdapter
import com.ansgar.rvhelper.createMultipleTypesAdapter
import com.ansgar.rvhelper.createSingleTypeAdapter
import com.ansgar.rvhelper.holders.DefaultLoadingViewHolder
import com.ansgar.rvhelper.models.ViewHolderItem
import com.ansgar.rvhelper.scroll.OnPageChanged
import com.ansgar.rvhelper.scroll.RvScrollListener
import com.ansgar.rvhelper.viewHoldersUtil
import com.ansgar.rvhelperexample.R
import kotlinx.android.synthetic.main.example_activity.*

class ExampleActivity : AppCompatActivity() {

    //    private lateinit var rvAdapter: SingleTypeAdapter<ExampleUser>
    private lateinit var rvAdapter: MultipleTypesAdapter
    private val viewHoldersUtil = viewHoldersUtil {
        create(R.layout.view_holder_user) { view -> UserViewHolder(view) }
        create(R.layout.view_holder_image) { view -> ImageViewHolder(view) }
        create(R.layout.view_holder_text) { view -> TextViewHolder(view) }
        createLoadingViewHolder { DefaultLoadingViewHolder(it) }
    }
    private val onRvScrollListener = RvScrollListener(object : OnPageChanged {
        override fun onPageChanged(page: Int) {
            runnable = Runnable {
                updateList(if(page < 3) getFakeUsers(page * 20 + 1) else ArrayList())
            }
            handler?.postDelayed(runnable, 500)
        }
    })

    // Handler and runnable just to simulate latency of backend response
    private var handler: Handler? = null
    private var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.example_activity)
        createRecyclerView()
        handler = Handler()
    }

    override fun onDestroy() {
        runnable?.let { handler?.removeCallbacks(it) }
        super.onDestroy()
    }

    private fun createRecyclerView() {
//        rvAdapter = viewHoldersUtil {
//            create(R.layout.view_holder_user) {
//                UserViewHolder(it, object : UserViewHolderListener {
//                    override fun onClickViewHolder(item: ExampleUser, position: Int) {
//                        item.name = "User name changed"
//                        rvAdapter.update(item, position)
//                    }
//                })
//            }
//        }.createSingleTypeAdapter { // <- returns adapter for your list
//            addAll(getFakeUsers())
//        }
        rvAdapter = viewHoldersUtil.createMultipleTypesAdapter {
            addAll(getFakeUsers(1))
            onScrollingObserver = onRvScrollListener
        }
        with(example_rv) {
            layoutManager = LinearLayoutManager(
                this@ExampleActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = rvAdapter
            addOnScrollListener(onRvScrollListener)
        }
    }

    private fun updateList(fakeItems: ArrayList<ViewHolderItem>) {
        rvAdapter.addAll(fakeItems)
    }

    private fun getFakeUsers(start: Int): ArrayList<ViewHolderItem> {
        val arrayList = ArrayList<ViewHolderItem>()
        for (i in start..(start + 19)) {
            when {
                i % 5 == 0 -> arrayList.add(
                    ExampleImage(
                        R.drawable.ic_launcher_background,
                        "$i This is view holder with text and images"
                    )
                )
                i % 3 == 0 -> arrayList.add(ExampleText("$i This is View Holder with text"))
                else -> arrayList.add(ExampleUser(i, "$i User name", "User last name"))
            }
        }
        return arrayList
    }

}