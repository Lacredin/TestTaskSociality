package ru.lacredin.testtasksociality.ui.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.lacredin.testtasksociality.App
import ru.lacredin.testtasksociality.R
import ru.lacredin.testtasksociality.databinding.FragmentDetailLocationBinding
import ru.lacredin.testtasksociality.extensions.gone
import ru.lacredin.testtasksociality.extensions.visible
import ru.lacredin.testtasksociality.models.episodes.EpisodeModel
import ru.lacredin.testtasksociality.models.fragment.StateFragment
import ru.lacredin.testtasksociality.models.locations.LocationModel
import ru.lacredin.testtasksociality.ui.locations.DetailLocationViewModel
import ru.lacredin.testtasksociality.utils.ID_ITEM
import javax.inject.Inject

class DetailEpisodeFragment: Fragment() {

    @Inject
    lateinit var viewModel: DetailEpisodeViewModel
    private var binding: FragmentDetailLocationBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailLocationBinding.inflate(inflater, container, false)

        val location = arguments?.getInt(ID_ITEM)
        viewModel.init(location)

        viewModel.data.observe(viewLifecycleOwner, ::setData)
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

    fun setData(data: EpisodeModel) {
        binding?.nameLocationValue?.text = data.name
        binding?.typeLocationValue?.text = data.episode
        binding?.dimensionLocationValue?.text = data.airDate
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