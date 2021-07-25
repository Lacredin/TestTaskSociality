package ru.lacredin.testtasksociality.ui.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.lacredin.testtasksociality.models.LocationsItem
import ru.lacredin.testtasksociality.models.fragment.StateFragment
import ru.lacredin.testtasksociality.repository.Repository

class DetailLocationViewModel : ViewModel() {

    val repository = Repository()

    var dataLocation: LocationsItem? = null
    protected val _data = MutableLiveData<LocationsItem>()
    val data: LiveData<LocationsItem>
        get() = _data

    protected val _state = MutableLiveData<Pair<StateFragment, String?>>()
    val state: LiveData<Pair<StateFragment, String?>>
        get() = _state

    fun init(data: LocationsItem?) {
        dataLocation = data
        _state.postValue(StateFragment.LOAD_DATA to null)
        dataLocation?.id?.let {
            repository.loadLocation(it.toString())
                .subscribe(
                    {
                        dataLocation = it
                        _data.postValue(dataLocation)
                        _state.postValue(StateFragment.SHOW_DATA to null)
                    },
                    {
                        _state.postValue(StateFragment.ERROR_LOAD to it.localizedMessage)
                    }
                )
        }

    }
}