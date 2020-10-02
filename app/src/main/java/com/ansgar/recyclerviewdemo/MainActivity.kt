package com.ansgar.recyclerviewdemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ansgar.rvhelper.holders.BaseViewHolderListener
import com.ansgar.rvhelper.adapters.MultipleTypesAdapter
import com.ansgar.rvhelper.models.ViewHolderItem
import com.ansgar.rvhelper.createMultipleTypesAdapter
import com.ansgar.rvhelper.holders.DefaultLoadingViewHolder
import com.ansgar.rvhelper.scroll.OnPageChanged
import com.ansgar.rvhelper.scroll.RvScrollListener
import com.ansgar.rvhelper.viewHoldersUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnPageChanged {

    private var handler: Handler? = null
    private var runnable: Runnable? = null

    private lateinit var rvAdapter: MultipleTypesAdapter
    private val onScrollListener = RvScrollListener(this@MainActivity)
    private val viewHoldersUtil = viewHoldersUtil {
        createLoadingViewHolder { DefaultLoadingViewHolder(it) }
        create(R.layout.view_holder_header) { HeaderViewHolder(it) }
        create(R.layout.view_holder_text) {
            TextViewHolder(it, object : TextViewHolderListener {
                override fun onTextClicked(user: User, position: Int) {
                    user.name = (0..1000).random().toString()
                    user.surName = (0..1000).random().toString()
                    rvAdapter.update(user, position)
                }

                override fun onLongClickedViewHolder(item: User, position: Int) {
                    rvAdapter.delete(position)
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
                    rvAdapter.notifyItemChanged(position)
                }

                override fun onLongClickedViewHolder(item: Image, position: Int) {
                    rvAdapter.add(
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        createRecyclerView()
        open_btn.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        handler = Handler()
        rv_example_refresher_srl.setOnRefreshListener {
            rvAdapter.refresh()
            updateList(generateList(0, 25))
            rv_example_refresher_srl.isRefreshing = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler?.removeCallbacks(runnable)
    }

    private fun createRecyclerView() {
        with(rv_example) {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                RecyclerView.VERTICAL,
                false
            )
            rvAdapter = viewHoldersUtil.createMultipleTypesAdapter {
                addAll(generateList(0, 25))
                onScrollingObserver = onScrollListener
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
            adapter = rvAdapter
            addOnScrollListener(onScrollListener)
        }
    }

    override fun onPageChanged(page: Int) {
        runnable = Runnable {
            updateList(if (page <= 3) generateList(page * 25 + 1, 25) else ArrayList())
        }
        handler?.postDelayed(runnable, 1000)
    }

    /**
     * All below is just for example
     */

    private fun updateList(items: ArrayList<ViewHolderItem>) {
        with(rvAdapter) {
//            updateAll(items)
            addAll(items)
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
                                "calls for a bold new mission design: a science probe dangling 20 miles (32 kilometers) below a cloud-borne balloon. " +
                                "A U.N.-declared international celebration of space science starts next week, and the public can participate in virtual events to mark the occasion. \n" +
                                "\n" +
                                "World Space Week is held each year from Oct. 4 to Oct. 10, and this year's programming will focus on satellites. World Space Week typically features space events hosted by participating space agencies, aerospace companies, museums, schools, and astronomy clubs, although the week will look different this year given public health measures in place to slow the spread of COVID-19. \n" +
                                "\n" +
                                "This year's theme is quite relevant to our increasingly virtual lifestyles. The broad benefits of satellites are \"things we don't normally think about; we take them for granted,\" Maruška Strah, executive director of World Space Week Association, told Space.com in an interview.\n" +
                                "\n" +
                                "Related: What is a satellite?" +
                                "\"Every single aspect about being able to connect right now [during the pandemic] is thanks to satellites,\" Strah added. \n" +
                                "\n" +
                                "World Space Week Association president Dennis Stone told Space.com that there is currently a revolution in the satellite industry. \"There's a range of accessibility and applications, so we try to get people to look very broadly this year at what's happening in the satellite world,\" Stone said.\n" +
                                "\n" +
                                "He highlighted the development of cubesats and low Earth orbit megaconstellations as examples of recent trends in the industry. Satellites can also be used to address global issues, Strah said, such as animal migrations and the spread of disease. \n" +
                                "\n" +
                                "Strah and Stone said that the pandemic and its social consequences will likely decrease participation in the week's events. \n" +
                                "\n" +
                                "\"This year we realize there's going to be a drop in the number of events because it's not safe to have public gatherings as we would in a normal year,\" Stone said, before adding that there are ways to bring science to home.\n" +
                                "\n" +
                                "\"We're also encouraging people to celebrate space and satellites in safe ways,\" Stone said. \"That could be as simple as parents taking their kids out in the backyard after sunset and looking for satellites in the evening and trying to learn what they are and get excited about what they can do.\"\n" +
                                "\n" +
                                "Strah is also optimistic about the global connection that virtual events can provide. Digital gatherings could bring people together from different parts of the world at the same time because there is no need to tackle travel constraints.\n" +
                                "\n" +
                                "World Space Week 2020 runs Oct. 4-Oct. 10. Visit the World Space Week website hereTo learn more about the U.N. project, how to join or host an event.\n" +
                                "\n" +
                                "Follow Doris Elin Urrutia on Twitter @salazar_elin. Follow us on Twitter @Spacedotcom and on Facebook. \n" +
                                "\n" +
                                "Join our Space Forums to keep talking space on the latest missions, night sky and more! And if you have a news tip, correction or comment, let us know at: community@space.com."
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