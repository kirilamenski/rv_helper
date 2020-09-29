package holders

interface BaseViewHolderListener<T> {
    fun onClickViewHolder(item: T, position: Int) {}

    fun onLongClickedViewHolder(item: T, position: Int) {}
}