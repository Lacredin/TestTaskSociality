package ru.lacredin.testtasksociality.ui.personage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import ru.lacredin.testtasksociality.App
import ru.lacredin.testtasksociality.R
import ru.lacredin.testtasksociality.databinding.FragmentDetailCharacterBinding
import ru.lacredin.testtasksociality.databinding.FragmentDetailLocationBinding
import ru.lacredin.testtasksociality.extensions.gone
import ru.lacredin.testtasksociality.extensions.visible
import ru.lacredin.testtasksociality.models.character.CharacterModel
import ru.lacredin.testtasksociality.models.locations.LocationModel
import ru.lacredin.testtasksociality.models.fragment.StateFragment
import ru.lacredin.testtasksociality.ui.locations.DetailLocationViewModel
import ru.lacredin.testtasksociality.utils.ID_ITEM
import javax.inject.Inject

class DetailCharacterFragment : Fragment() {

    @Inject
    lateinit var viewModel: DetailCharacterViewModel
    private var binding: FragmentDetailCharacterBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailCharacterBinding.inflate(inflater, container, false)

        val location = arguments?.getInt(ID_ITEM)
        viewModel.init(location)

        viewModel.locationLiveData.observe(viewLifecycleOwner, ::setLocationData)
        viewModel.characterLiveData.observe(viewLifecycleOwner, ::setCharacterData)

        viewModel.state.observe(viewLifecycleOwner, {
            when (it.first) {
                StateFragment.LOAD_DATA -> showProgress()
                StateFragment.SHOW_DATA -> hideProgress()
                StateFragment.ERROR_LOAD -> showError(
                    it.second ?: getString(R.string.unknown_error)
                )
            }
        })

        return binding?.root
    }

    fun setCharacterData(data: CharacterModel) {
        binding?.characterName?.text = data.name
        binding?.characterSpecies?.text = data.species
        binding?.characterStatus?.text = data.status
        binding?.characterGender?.text = data.gender

        binding?.characterImage?.let {
            Picasso.get()
                .load(data.image)
                .placeholder(R.drawable.avatar_1)
                .into(it)
        }
    }

    fun setLocationData(data: LocationModel) {
        binding?.nameLocationValue?.text = data.name
        binding?.typeLocationValue?.text = data.type
        binding?.dimensionLocationValue?.text = data.dimension
    }

    fun showProgress() {
        binding?.messageBox?.visible()
        binding?.progressBar?.visible()
        binding?.message?.text = getString(R.string.progress_bar_message)
    }

    fun hideProgress() {
        binding?.messageBox?.gone()
    }

    fun showError(message: String) {
        binding?.messageBox?.visible()
        binding?.progressBar?.gone()
        binding?.message?.text = message
    }
}