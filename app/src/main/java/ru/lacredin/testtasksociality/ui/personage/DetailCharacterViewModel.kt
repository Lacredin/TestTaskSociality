package ru.lacredin.testtasksociality.ui.personage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.lacredin.testtasksociality.App
import ru.lacredin.testtasksociality.models.character.CharacterModel
import ru.lacredin.testtasksociality.models.fragment.StateFragment
import ru.lacredin.testtasksociality.models.locations.LocationModel
import ru.lacredin.testtasksociality.repository.Repository
import javax.inject.Inject

class DetailCharacterViewModel @Inject constructor() : ViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var repository: Repository

    var dataCharacter: CharacterModel? = null
    var dataLocation: LocationModel? = null
    protected val _dataLocation = MutableLiveData<LocationModel>()
    val locationLiveData: LiveData<LocationModel>
        get() = _dataLocation

    protected val _dataCharacter = MutableLiveData<CharacterModel>()
    val characterLiveData: LiveData<CharacterModel>
        get() = _dataCharacter

    protected val _state = MutableLiveData<Pair<StateFragment, String?>>()
    val state: LiveData<Pair<StateFragment, String?>>
        get() = _state

    fun init(data: Int?) {
        _state.postValue(StateFragment.LOAD_DATA to null)
        data?.let {
            repository.loadCharacter(it.toString())
                .map { data ->
                    dataCharacter = data
                    _dataCharacter.postValue(data)
                    data.location?.url?.lastIndexOf("/")?.let {
                        data.location?.url!!.substring(it + 1)
                    }
                }
                .flatMap {
                    repository.loadLocation(it)
                }
                .subscribe(
                    {
                        dataLocation = it
                        _dataLocation.postValue(dataLocation)
                        _state.postValue(StateFragment.SHOW_DATA to null)
                    },
                    {
                        _state.postValue(StateFragment.ERROR_LOAD to it.localizedMessage)
                    }
                )
        }

    }
}