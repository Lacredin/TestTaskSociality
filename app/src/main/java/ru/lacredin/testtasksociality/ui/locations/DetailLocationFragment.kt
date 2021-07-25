package ru.lacredin.testtasksociality.ui.locations

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
import ru.lacredin.testtasksociality.models.LocationsItem
import ru.lacredin.testtasksociality.models.fragment.StateFragment
import javax.inject.Inject

class DetailLocationFragment : Fragment() {

    @Inject
    lateinit var viewModel: DetailLocationViewModel
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

        val location = arguments?.getParcelable<LocationsItem>("LOCATIONS")
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

    fun setData(data: LocationsItem) {
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