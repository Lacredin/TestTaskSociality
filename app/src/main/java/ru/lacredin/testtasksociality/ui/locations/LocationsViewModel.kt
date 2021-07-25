  package ru.lacredin.testtasksociality.ui.locations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.lacredin.testtasksociality.R
import ru.lacredin.testtasksociality.models.LocationsItem
import ru.lacredin.testtasksociality.repository.Repository

class LocationsViewModel : ViewModel() {

    val repository = Repository()
    var listLocations = mutableListOf<LocationsItem>()
    val listItems = MutableLiveData<List<LocationsItem>>()
    val listNextPage = MutableLiveData<List<LocationsItem>>()
    val listPrevPage = MutableLiveData<List<LocationsItem>>()
    val loadData = MutableLiveData<Boolean>()
    val message = MutableLiveData<@androidx.annotation.StringRes Int>()
    var nextPageLocation: String? = null
    var prevPageLocation: String? = null

    fun loadAllLocations() {
        loadData.postValue(true)
        repository.loadAllLocation()
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
                    listItems.postValue(listLocations)
                },
                {
                    message.postValue(R.string.unknown_error)
                }
            )
    }

    fun loadNextLocation() {
        loadData.postValue(true)
        repository.loadPageLocation(nextPageLocation ?: return)
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
                    listItems.postValue(listLocations)
                },
                {
                    message.postValue(R.string.unknown_error)
                }
            )
    }

    fun loadPrevLocation() {
        loadData.postValue(true)
        repository.loadPageLocation(prevPageLocation ?: return)
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
                    listItems.postValue(listLocations)
                },
                {
                    message.postValue(R.string.unknown_error)
                }
            )
    }
}