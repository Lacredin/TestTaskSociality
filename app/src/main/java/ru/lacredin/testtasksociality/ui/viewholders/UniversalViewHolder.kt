package ru.lacredin.testtasksociality.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.lacredin.testtasksociality.ui.items.BaseItem

class UniversalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: BaseItem) {
        item.bind(itemView)
    }
}