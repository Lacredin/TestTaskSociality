package ru.lacredin.testtasksociality.ui.locations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.lacredin.testtasksociality.App
import ru.lacredin.testtasksociality.R
import ru.lacredin.testtasksociality.models.locations.LocationModel
import ru.lacredin.testtasksociality.repository.Repository
import javax.inject.Inject

class LocationsViewModel @Inject constructor() : ViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var repository: Repository
    var listLocations = mutableListOf<LocationModel>()
    val listItems = MutableLiveData<List<LocationModel>>()
    val listNextPage = MutableLiveData<List<LocationModel>>()
    val listPrevPage = MutableLiveData<List<LocationModel>>()
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