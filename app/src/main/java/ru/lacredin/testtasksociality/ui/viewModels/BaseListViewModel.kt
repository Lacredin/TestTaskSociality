package ru.lacredin.testtasksociality.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import ru.lacredin.testtasksociality.R
import ru.lacredin.testtasksociality.models.ResultListModel
import ru.lacredin.testtasksociality.ui.items.BaseItem

abstract class BaseListViewModel<T> : ViewModel() {

    var listLocations = mutableListOf<T>()
    val listItems = MutableLiveData<List<BaseItem>>()
    val listNextPage = MutableLiveData<List<BaseItem>>()
    val listPrevPage = MutableLiveData<List<BaseItem>>()
    val loadData = MutableLiveData<Boolean>()
    val message = MutableLiveData<@androidx.annotation.StringRes Int>()
    var nextPageLocation: String? = null
    var prevPageLocation: String? = null
    val openItemFragment = MutableLiveData<Int?>()

    abstract fun getBaseRequest(): Single<ResultListModel<T>>
    abstract fun getPageRequest(pageUrl: String): Single<ResultListModel<T>>
    abstract fun createItems(data: List<T>): List<BaseItem>

    fun openFragment(id: Int) {
        openItemFragment.postValue(id)
    }

    fun clearOpenFragment() {
        openItemFragment.postValue(null)
    }

    fun loadBaseRequest() {
        loadData.postValue(true)
        getBaseRequest()
            .map {
                nextPageLocation = it.info?.next
                prevPageLocation = it.info?.prev
                it.results?.let {
                    listLocations.clear()
                    listLocations.addAll(it)
                }
//                listItems.value
            }
            .doFinally { loadData.postValue(false) }
            .subscribe(
                {
                    listItems.postValue(createItems(listLocations))
                },
                {
                    message.postValue(R.string.unknown_error)
                }
            )
    }

    fun loadNextLocation() {
        loadData.postValue(true)
        getPageRequest(nextPageLocation ?: return)
            .map {
                nextPageLocation = it.info?.next
                prevPageLocation = it.info?.prev
                it.results?.let {
                    listLocations.addAll(it)
                }
            }
            .doFinally { loadData.postValue(false) }
            .subscribe(
                {
                    listItems.postValue(createItems(listLocations))
                },
                {
                    message.postValue(R.string.unknown_error)
                }
            )
    }

    fun loadPrevLocation() {
        loadData.postValue(true)
        getPageRequest(prevPageLocation ?: return)
            .map {
                nextPageLocation = it.info?.next
                prevPageLocation = it.info?.prev
                it.results?.let {
                    listLocations.addAll(0, it)
                }
            }
            .doFinally { loadData.postValue(false) }
            .subscribe(
                {
                    listItems.postValue(createItems(listLocations))
                },
                {
                    message.postValue(R.string.unknown_error)
                }
            )
    }
}