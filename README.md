## Description

RvHelper is the library which helps you to create lists in android application without writing an adapter.
This is a handy extension for the RecyclerView Adapter written in kotlin that use DSL.

## Features

1. Creating lists with one type view and with several.
2. Provide the ability to create loading view at the bottom of list by default.

- [Dependencies](#dependencies)
- [Single view type adapter](#single-view-type-adapter)
- [View holder listener](#view-holder-listener)
- [Multiple view types adapter](#multiple-view-types-adapter)
   - [Default loading view holder](#default-loading-view-holder)
   - [Refresh list](#refresh-list)
- [Update list with DiffUtil](#update-list-with-diff-util)


## Dependencies
At the following code to your project level build.gradle file
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
and to your app level build.gradle:
```gradle
dependencies {
    implementation 'com.github.kirilamenski:rv_helper:1.1.6'
}
```

## Single view type adapter
You should create ViewHoldersUtil class where you can create your view holder class. viewHoldersUtil {} will return you container of your view holders then you can call adapter by createSingleTypeAdapter. To add view holder to lists in viewHoldersUtil you need to call create method and pass layoutResId and define View Holder in callback. That callback called when onCreateViewHolder in RecyclerView.Adapter executed and returns the parent of Recycler View.
To create an adapter you must call createSingleTypeAdapter{} and define the type of model that will be stored in the list. In createSingleTypeAdapter block you can manage RecycleView.Adapter. 
There are several methods here by default. For example ``` addAll(fakeUsers)``` it allow you to pass your data to the list in adapter.
```kotlin
private val viewHoldersUtil = viewHoldersUtil {
    create(R.layout.view_holder_user) { parent -> 
      UserViewHolder(
        ViewHolderUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      )
    }
}
private lateinit var rvAdapter: SingleTypeAdapter<User>

...

private fun createRecyclerView() {
    rvAdapter = viewHoldersUtil.createSingleTypeAdapter {
        addAll(getFakeUsers())
    }
    with(recyclerView) {
        layoutManager = LinearLayoutManager(
            this@SingleTypeAdapterActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = rvAdapter
    }
}
```
When you create your View Holder class you should extend BaseViewHolder, define your model type and override bindModel. In this method you can bind values to ui components
```kotlin 
class UserViewHolder(private val binding: ViewHolderUserBinding) : BaseViewHolder<User>(view) {
  override fun bindModel(item: User) {
      binding.userNameTv.text = item.name
  }
}
```
<img src="https://i.imgur.com/FlSqweZ.png" width="250" height="430" />

## View holder listener
This library is also provide the ability to manage button click events inside your View Holder. There is an interface for this BaseViewHolderListener where two basic methods are defined onClick and onLongClick. If you need additional events you can create your own listener and extend BaseViewHolderListener. You can pass listener in create method when you define your View Holder.
Base Recycler View provide few method that you can use to update your model in list (for example ```rvAdapter.update(item, position)```). After changes:
```kotlin
private val viewHoldersUtil = viewHoldersUtil {
    create(R.layout.view_holder_user) { parent-> 
        UserViewHolder(
          UserViewHolder(
            ViewHolderUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
          ),
          object : UserViewHolderListener {
            override fun onClickViewHolder(item: User, position: Int) {
                  item.name = "User name changed"
                  rvAdapter.update(item, position)
                }
            })
        }
    }
private lateinit var rvAdapter: SingleTypeAdapter<User>

...

private fun createRecyclerView() {
    rvAdapter = viewHoldersUtil.createSingleTypeAdapter {
        addAll(getFakeUsers())
    }
    with(recyclerView) {
        layoutManager = LinearLayoutManager(
            this@SingleTypeAdapterActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = rvAdapter
    }
}
...
```
<img src="https://i.imgur.com/yloCOnZ.gif" width="250" height="430"/>

## Multiple view types adapter
The main difference between singleTypeAdapter and multipleTypeAdapter is that to create a list with many types, your models must inherit ViewHolderItem class.
```kotlin
data class ExampleUser(
    val id: Int,
    val name: String,
    val surName: String
): ViewHolderItem()

data class ExampleText(
    val text: String
): ViewHolderItem()

data class ExampleImage(
    @DrawableRes val drawableRes: Int,
    val imageTitle: String
) : ViewHolderItem()
```
After that you need to create View Holders for these items
```kotlin
private val viewHoldersUtil = viewHoldersUtil {
  create(R.layout.view_holder_user, ExampleUser::class.java) { parent ->
    UserViewHolder(
      ViewHolderUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }
  create(R.layout.view_holder_image, ExampleImage::class.java) { parent ->
    ImageViewHolder(
      ViewHolderImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }
  create(R.layout.view_holder_text, ExampleText::class.java) { parent ->
    TextViewHolder(
      ViewHolderTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }
}
```
To create an adapter with many types you need to call createMultipleTypesAdapter instead of createSingleTypeAdapter
```kotlin
private fun createRecyclerView() {
    rvAdapter = viewHoldersUtil.createMultipleTypesAdapter {
        addAll(getFakeItems())
    }
    with(recyclerView) {
        layoutManager = LinearLayoutManager(
            this@SingleTypeAdapterActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = rvAdapter
    }
}
```
<img src="https://i.imgur.com/zOQcSPf.gif" width="250" height="430"/>

## Default loading view holder
If your project use pagination and you want to add Loading View Holder at the bottom of the list you can modified code by following:
```kotlin
private val viewHoldersUtil = viewHoldersUtil {
  create(R.layout.view_holder_user, ExampleUser::class.java) { parent ->
    UserViewHolder(
      ViewHolderUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }
  create(R.layout.view_holder_image, ExampleImage::class.java) { parent ->
    ImageViewHolder(
      ViewHolderImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }
  create(R.layout.view_holder_text, ExampleText::class.java) { parent ->
    TextViewHolder(
      ViewHolderTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }
  //This will add default loading to your list
  createLoadingViewHolder { parent ->
    DefaultLoadingViewHolder(
      ViewHolderDefaultLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
  }
}
```
You can also add your own custom loading
```kotlin
private val viewHoldersUtil = viewHoldersUtil {
  ...
  createLoadingViewHolder(R.layout.you_custom_view_holder) { parent -> 
    CustomViewHolder(YourCustomViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }
}
```
We also need to know when the list was scrolled to the bottom. To archive this we should add callback to RecyclerView ```RecyclerView.addOnScrollListener()```.
Also, when you create adapter you can add scroll observer to BaseAdapter. It will help you to not repeat request if previous response was empty.
When you have scrolled to the very bottom and there are no more values in the answer, you can remove the default loading view holder by sending an empty list.
```kotlin
private val onRvScrollListener = RvScrollListener(object : OnPageChanged {
    override fun onPageChanged(page: Int) {
        // At first time this callback return page == 1. N is your desired number of values.
        // Condition added just for example when new data is missing.
        updateList(if(page < 3) getFakeUsers(page * N) else ArrayList())
    }
})
    
private fun createRecyclerView() {
    rvAdapter = viewHoldersUtil.createSingleTypeAdapter {
        addAll(getFakeUsers())
        onScrollingObserver = onRvScrollListener // Adding scroll observer to adapter
    }
    with(recyclerView) {
        layoutManager = LinearLayoutManager(
            this@SingleTypeAdapterActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = rvAdapter
        addOnScrollListener(onRvScrollListener) // Adding scroll listener to RecyclerView
    }
}
```
<img src="https://i.imgur.com/JeaYDea.gif" width="250" height="430"/>

> :warning: **WARNING!** Keep in mind that the default holder does not support the timeout error. You can implement it yourself using some Handler where you can call ```rvAdapter.deleteLoadingViewHolder()```. It will remove the last view holder if it is the DefaultLoadingViewHolder (or your custom loading view holder).

## Refresh list
In xml wrap your RecyclerView in SwipeRefreshLayout:
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.swiperefreshlayout.widget.SwipeRefreshLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/rv_refresher_srl"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/example_rv"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</androidx.swiperefreshlayout.widget.SwipeRefreshLayout>
```
And set onRefreshListener:
```kotlin
listRefresher.setOnRefreshListener {
    rvAdapter.refresh() // this method updates the list in the adapter 
    updateList(fakeItems)
    listRefresher.isRefreshing = false
}
```

<img src="https://i.imgur.com/fpsxjfZ.gif" width="250" height="430"/>

## Update list with DiffUtil
Also, this library provide the ability to update yourList with DiffUtil. To do this you need to define two callbacks ```onItemSame```, and ```onContentSame``` where you must write the necessary condition.
```kotlin
private fun createRecyclerView() {
    rvAdapter = viewHoldersUtil.createSingleTypeAdapter {
        addAll(getFakeUsers())
    }
    with(recyclerView) {
        layoutManager = LinearLayoutManager(
            this@SingleTypeAdapterActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = rvAdapter
        onItemsSame = { oldItem, newItem ->
            true
        }
        onContentSame = { oldItem, newItem ->
            true
        }
    }
}
```
rvAdapter has a method to update the list using DiffUtil
```kotlin
rvAdapter.updateAll(newItems)
```
