package ru.lacredin.testtasksociality.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_location.view.*
import ru.lacredin.testtasksociality.models.LocationsItem

class LocationViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    var data: LocationsItem? = null

    fun View.init() {
        location_name.text = data?.name
        location_type.text = data?.type
        location_dimension.text = data?.dimension
    }

    fun bind(data: LocationsItem) {
        this.data = data
        itemView.apply {
            init()
        }
    }


}