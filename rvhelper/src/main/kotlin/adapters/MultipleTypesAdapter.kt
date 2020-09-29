package adapters

import models.ViewHolderItem

/**
 * Adapter use to create multiple [RecyclerView] with multiple view types.
 * [ViewHolderItem] is the basic class which contain layoutRes that [RecyclerView.getItemViewType]
 * use to create many different [ViewHolder].
 */
open class MultipleTypesAdapter : BaseAdapter<ViewHolderItem>() {

    /**
     * [items.layoutRes] used as view holder type
     */
    override fun getItemViewType(position: Int): Int = items[position].layoutRes

}
