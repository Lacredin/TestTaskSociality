package ru.lacredin.testtasksociality.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.lacredin.testtasksociality.databinding.FragmentDetailLocationBinding
import ru.lacredin.testtasksociality.models.LocationsItem

class DetailLocationFragment : Fragment() {

    private lateinit var locationsViewModel: DetailLocationViewModel
    private var binding: FragmentDetailLocationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        locationsViewModel = ViewModelProvider(this).get(DetailLocationViewModel::class.java)
        binding = FragmentDetailLocationBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val location = arguments?.getParcelable<LocationsItem>("LOCATIONS")
        binding?.nameLocationValue?.text = location?.name
        binding?.typeLocationValue?.text = location?.type
        binding?.dimensionLocationValue?.text = location?.dimension

        findNavController()
    }
}