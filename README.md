## Description

RvHelper is the library which helps you to create lists in android application without writing an adapter.
This is a handy extension for the RecyclerView Adapter written in kotlin that use DSL.

## Features

1. Creating lists with one type view and with several.
2. Provide the ability to create loading view at the bottom of list by default.

## How to use

- [Dependencies](#dependencies)
- [Single view type adapter](#single-view-type-adapter)
- [View holder listener](#view-holder-listener)
- [Multiple view types adapter](#multiple-view-types-adapter)
   - [Default loading view holder]()
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
    implementation 'com.github.User:Repo:Tag'
}
```

## Single view type adapter
You should create ViewHoldersUtil class where you can create your view holder class. viewHoldersUtil {} will return you container of your view holders then you can call adapter by createSingleTypeAdapter. To add view holder to lists in viewHoldersUtil you need to call create method and pass layoutResId and define View Holder in callback. That callback called when onCreateViewHolder in RecyclerView.Adapter executed and returns the view created by LayoutInflater.
To create an adapter you must call createSingleTypeAdapter{} and define the type of model that will be stored in the list. In createSingleTypeAdapter block you can manage RecycleView.Adapter. 
There are several methods here by default. For example ``` addAll(fakeUsers)``` it allow you to pass your data to the list in adapter.
```kotlin
private val viewHoldersUtil = viewHoldersUtil {
    create(R.layout.view_holder_user) { view-> UserViewHolder(view) }
}
private lateinit var rvAdapter: SingleTypeAdapter<User>

...

private fun createRecyclerView() {
    rvAdapter = viewHoldersUtil.createSingleTypeAdapter {
        addAll(getFakeUsers())
    }
    with(single_type_adapter_rv) {
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
class UserViewHolder(view: View) : BaseViewHolder<User>(view) {
  override fun bindModel(item: User) {
      with(itemView) {
          user_name_tv.text = item.name
      }
  }
}
```
<img src="https://i.imgur.com/FlSqweZ.png" width="250" height="430" />

## View holder listener
This library is also provide the ability to manage button click events inside your View Holder. There is an interface for this BaseViewHolderListener where two basic methods are defined onClick and onLongClick. If you need additional events you can create your own listener and extend BaseViewHolderListener. You can pass listener in create method when you define your View Holder.
Base Recycler View provide few method that you can use to update your model in list (for example ```rvAdapter.update(item, position)```). After changes:
```kotlin
private val viewHoldersUtil = viewHoldersUtil {
    create(R.layout.view_holder_user) { view-> 
        UserViewHolder(view, object : UserViewHolderListener {
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
    with(single_type_adapter_rv) {
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
The main difference between singleTypeAdapter and multipleTypeAdapter is that to create a list with many types, your models must inherit ViewHolderItem class. This class contains type which is @LayoutRes of view holder layout.
```kotlin
data class ExampleUser(
    var id: Int,
    var name: String,
    var surName: String
): ViewHolderItem(R.layout.view_holder_user)

data class ExampleText(
    val text: String
): ViewHolderItem(R.layout.view_holder_text)

data class ExampleImage(
    @DrawableRes val drawableRes: Int,
    val imageTitle: String
) : ViewHolderItem(R.layout.view_holder_example_image)
```
After that you need to create View Holders for these items
```kotlin
private val viewHoldersUtil = viewHoldersUtil {
    create(R.layout.view_holder_user) { view -> UserViewHolder(view) }
    create(R.layout.view_holder_image) { view -> ImageViewHolder(view) }
    create(R.layout.view_holder_text) { view -> TextViewHolder(view) }
}
```
To create an adapter with many types you need to call createMultipleTypesAdapter instead of createSingleTypeAdapter
```kotlin
private fun createRecyclerView() {
    rvAdapter = viewHoldersUtil.createMultipleTypesAdapter {
        addAll(getFakeItems())
    }
    with(single_type_adapter_rv) {
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

## Update list with DiffUtil
