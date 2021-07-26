package ru.lacredin.testtasksociality.ui.base

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.lacredin.testtasksociality.ui.viewModels.BaseListViewModel
import ru.lacredin.testtasksociality.utils.CustomRecycleAdapter
import ru.lacredin.testtasksociality.utils.ID_ITEM
import ru.lacredin.testtasksociality.utils.PaginationListener

abstract class BaseListFragment : Fragment() {

    lateinit var adapter: CustomRecycleAdapter
    lateinit var paginationListener: PaginationListener
    var loading = true

    fun <T> initListViewModel(
        listViewModel: BaseListViewModel<T>,
        swipeRefreshBox: SwipeRefreshLayout
    ) {
        listViewModel.listItems.observe(viewLifecycleOwner) {
            adapter.listData.clear()
            adapter.listData.addAll(it)
            adapter.notifyDataSetChanged()
            swipeRefreshBox.isRefreshing = false
        }

        listViewModel.listNextPage.observe(viewLifecycleOwner) {
            adapter.listData.addAll(it)
        }

        listViewModel.listPrevPage.observe(viewLifecycleOwner) {
            adapter.listData.addAll(0, it)
        }

        listViewModel.message.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            swipeRefreshBox.isRefreshing = false
        }

        listViewModel.loadData.observe(viewLifecycleOwner) {
            loading = it
        }

        listViewModel.openItemFragment.observe(viewLifecycleOwner) {
            it?.let { it1 -> openDetailEpisode(it1) }
            listViewModel.clearOpenFragment()
        }
    }

    fun openDetailEpisode(id: Int) {
        findNavController().navigate(getIdDetailFragment(), Bundle().apply {
            putInt(ID_ITEM, id)
        })
    }

    abstract fun getIdDetailFragment(): Int

    fun <T> settingRecycleView(listElements: RecyclerView, listViewModel: BaseListViewModel<T>) {
        listElements.adapter = adapter
        listElements.layoutManager = LinearLayoutManager(context).apply {
            createPagination(this, listViewModel)
        }
        listElements.addOnScrollListener(paginationListener)
        listElements.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayout.VERTICAL
            )
        )
    }

    fun <T> createPagination(
        layoutManager: LinearLayoutManager,
        listViewModel: BaseListViewModel<T>
    ) {
        paginationListener = object : PaginationListener(layoutManager) {
            override fun loadMoreItems() {
                listViewModel.loadNextLocation()
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

    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}