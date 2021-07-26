package ru.lacredin.testtasksociality.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ru.lacredin.testtasksociality.App
import ru.lacredin.testtasksociality.R
import ru.lacredin.testtasksociality.databinding.FragmentListLocationBinding
import ru.lacredin.testtasksociality.models.locations.LocationModel
import ru.lacredin.testtasksociality.utils.PaginationListener
import ru.lacredin.testtasksociality.utils.RecycleAdapter
import javax.inject.Inject

class LocationsFragment : Fragment() {

    @Inject
    lateinit var locationsViewModel: LocationsViewModel
    private var _binding: FragmentListLocationBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: RecycleAdapter
    lateinit var paginationListener: PaginationListener
    var loading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListLocationBinding.inflate(inflater, container, false)

        adapter = RecycleAdapter(context) {
            findNavController().navigate(R.id.detailLocationFragment, Bundle().apply {
                putParcelable("LOCATIONS", it as LocationModel)
            })
        }

        locationsViewModel.listItems.observe(viewLifecycleOwner) {
            adapter.listData.clear()
            adapter.listData.addAll(it)
            adapter.notifyDataSetChanged()
            binding.swipeRefresheBox.isRefreshing = false
        }

        locationsViewModel.listNextPage.observe(viewLifecycleOwner) {
            adapter.listData.addAll(it)
        }

        locationsViewModel.listPrevPage.observe(viewLifecycleOwner) {
            adapter.listData.addAll(0, it)
        }

        locationsViewModel.message.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            binding.swipeRefresheBox.isRefreshing = false
        }

        locationsViewModel.loadData.observe(viewLifecycleOwner) {
            loading = it
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listElements.adapter = adapter
        binding.listElements.layoutManager = LinearLayoutManager(context).apply {
            createPagination(this)
        }
        binding.listElements.addOnScrollListener(paginationListener)
        binding.listElements.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayout.VERTICAL
            )
        )

        binding.swipeRefresheBox.setOnRefreshListener {
            locationsViewModel.loadAllLocations()
        }

        locationsViewModel.loadAllLocations()
    }

    fun createPagination(layoutManager: LinearLayoutManager) {
        paginationListener = object : PaginationListener(layoutManager) {
            override fun loadMoreItems() {
                locationsViewModel.loadNextLocation()
                adapter.setStatusLoadItem(true)
            }

            override val isLastPage: Boolean
                get() = false
            override val isLoading: Boolean
                get() = loading
            override val pageSize: Int
                get() = adapter.getSize()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}