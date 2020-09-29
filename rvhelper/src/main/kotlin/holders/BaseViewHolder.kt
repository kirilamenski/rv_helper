package holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<VM>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bindModel(item: VM)

}
