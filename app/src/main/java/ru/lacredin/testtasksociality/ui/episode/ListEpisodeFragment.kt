package ru.lacredin.testtasksociality.ui.episode

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
import ru.lacredin.testtasksociality.utils.CustomRecycleAdapter
import ru.lacredin.testtasksociality.utils.PaginationListener
import javax.inject.Inject

class ListEpisodeFragment : Fragment() {

    @Inject
    lateinit var viewModel: ListEpisodeViewModel
    private var _binding: FragmentListLocationBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: CustomRecycleAdapter
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

        adapter = CustomRecycleAdapter(context)

        viewModel.listItems.observe(viewLifecycleOwner) {
            adapter.listData.clear()
            adapter.listData.addAll(it)
            adapter.notifyDataSetChanged()
            binding.swipeRefresheBox.isRefreshing = false
        }

        viewModel.listNextPage.observe(viewLifecycleOwner) {
            adapter.listData.addAll(it)
        }

        viewModel.listPrevPage.observe(viewLifecycleOwner) {
            adapter.listData.addAll(0, it)
        }

        viewModel.message.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            binding.swipeRefresheBox.isRefreshing = false
        }

        viewModel.loadData.observe(viewLifecycleOwner) {
            loading = it
        }

        viewModel.openItemFragment.observe(viewLifecycleOwner) {
            it?.let { it1 -> openDetailEpisode(it1) }
            viewModel.clearOpenFragment()
        }

        return binding.root
    }

    fun openDetailEpisode(id: Int) {
        findNavController().navigate(R.id.detailLocationFragment, Bundle().apply {
            putInt("LOCATIONS", id)
        })
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
            viewModel.loadBaseRequest()
        }

        viewModel.loadBaseRequest()
    }

    fun createPagination(layoutManager: LinearLayoutManager) {
        paginationListener = object : PaginationListener(layoutManager) {
            override fun loadMoreItems() {
                viewModel.loadNextLocation()
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

    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}