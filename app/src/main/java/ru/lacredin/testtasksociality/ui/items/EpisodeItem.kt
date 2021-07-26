package ru.lacredin.testtasksociality.ui.items

import android.view.View
import kotlinx.android.synthetic.main.item_location.view.*
import ru.lacredin.testtasksociality.R
import ru.lacredin.testtasksociality.models.episodes.EpisodeModel

class EpisodeItem(
    val data: EpisodeModel,
    val clickItem: (EpisodeItem) -> Unit
) : BaseItem() {
    override fun getLayoutId() = R.layout.item_location

    override fun bind(viewItem: View) {
        viewItem.apply {
            location_name.text = data.name
            location_type.text = data.episode
            location_dimension.text = data.created
            location_main_box.setOnClickListener { clickItem(this@EpisodeItem) }
        }
    }
}