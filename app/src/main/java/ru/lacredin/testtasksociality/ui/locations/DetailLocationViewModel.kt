package ru.lacredin.testtasksociality.ui.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.lacredin.testtasksociality.App
import ru.lacredin.testtasksociality.models.locations.LocationModel
import ru.lacredin.testtasksociality.models.fragment.StateFragment
import ru.lacredin.testtasksociality.repository.Repository
import javax.inject.Inject

class DetailLocationViewModel @Inject constructor(): ViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var repository: Repository

    var dataLocation: LocationModel? = null
    protected val _data = MutableLiveData<LocationModel>()
    val data: LiveData<LocationModel>
        get() = _data

    protected val _state = MutableLiveData<Pair<StateFragment, String?>>()
    val state: LiveData<Pair<StateFragment, String?>>
        get() = _state

    fun init(data: LocationModel?) {
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