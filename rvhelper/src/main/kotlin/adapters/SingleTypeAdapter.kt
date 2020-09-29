package adapters

class SingleTypeAdapter<VM> : BaseAdapter<VM>() {

    override fun getItemViewType(position: Int): Int = viewHoldersUtil.viewHolders.keyAt(0)

}