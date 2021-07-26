package ru.lacredin.testtasksociality.ui.items

import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_character.view.*
import ru.lacredin.testtasksociality.R
import ru.lacredin.testtasksociality.models.character.CharacterModel

class CharacterItem(
    val data: CharacterModel,
    val clickItem: (CharacterItem) -> Unit
) : BaseItem() {
    override fun getLayoutId() = R.layout.item_character

    override fun bind(viewItem: View) {
        viewItem.apply {
            character_name.text = data.name
            character_species.text = data.species
            character_status.text = data.status
            character_gender.text = data.gender
            character_main_box.setOnClickListener {
                clickItem(this@CharacterItem)
            }

            Picasso.get()
                .load(data.image)
                .placeholder(R.drawable.avatar_1)
                .into(character_image)
        }
    }
}