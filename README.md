## Description

RvHelper is the library which helps you to create lists in android application without writing an adapter.
This is a handy extension for the RecyclerView Adapter written in kotlin that use DSL.

## Features

1. Creating lists with one type view and with several.
2. Provide the ability to create loading view at the bottom of list by default.

## How to use

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

## How to use
### Singly view type adapter
```kotlin
private fun createRecyclerView() {
    with(single_type_adapter_rv) {
        layoutManager = LinearLayoutManager(
            this@SingleTypeAdapterActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        // viewHoldersUtil is a helper class that contains your view holders and where you should define the view holders
        adapter = viewHoldersUtil {
            // you need to call create function and pass @LayoutRes layoutResId and you view holder class
            create(R.layout.view_holder_user) { UserViewHolder(it) }
        }.createSingleTypeAdapter<User> { // <- returns adapter for your list
            // to pass values to a list
            addAll(getFakeUsers())
        }
    }
}
    
// Your View Holder Class should extend BaseViewHolder<User>(view)
class UserViewHolder(view: View) : BaseViewHolder<User>(view) {
  // bind model called in RecyclerView.Adapter when onBindViewHolder is executed
  // here you can bind values to your components
  override fun bindModel(item: User) {
      with(itemView) {
          vh_user_name_tv.text = item.name
      }
  }
}
```
<img src="https://i.imgur.com/FlSqweZ.png" width="250" height="430" />

Lets change code if you want to control the click of you views:
```kotlin
// viewHoldersUtil is a helper class that contains your view holders and where you should define the view holders
private val viewHoldersUtil = viewHoldersUtil {
    // you need to call create function and pass @LayoutRes layoutResId and you view holder class
    create(R.layout.view_holder_user) {
        UserViewHolder(it, object : UserViewHolderListener {
            override fun onClickViewHolder(item: User, position: Int) {
                item.name = "User name changed"
                    rvAdapter.update(item, position) // <- function defined in base adapter class to allow update items in the list.
                }
            })
        }
    }
private lateinit var rvAdapter: SingleTypeAdapter<User>

...

private fun createRecyclerView() {
    with(single_type_adapter_rv) {
        layoutManager = LinearLayoutManager(
            this@SingleTypeAdapterActivity,
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
...
```
![](https://i.imgur.com/yloCOnZ.gif)
