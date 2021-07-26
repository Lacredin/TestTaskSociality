package ru.lacredin.testtasksociality.ui.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.lacredin.testtasksociality.App
import ru.lacredin.testtasksociality.models.episodes.EpisodeModel
import ru.lacredin.testtasksociality.models.fragment.StateFragment
import ru.lacredin.testtasksociality.repository.Repository
import javax.inject.Inject

class DetailEpisodeViewModel @Inject constructor() : ViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var repository: Repository

    var dataEpisode: EpisodeModel? = null
    protected val _data = MutableLiveData<EpisodeModel>()
    val data: LiveData<EpisodeModel>
        get() = _data

    protected val _state = MutableLiveData<Pair<StateFragment, String?>>()
    val state: LiveData<Pair<StateFragment, String?>>
        get() = _state

    fun init(data: Int?) {
        _state.postValue(StateFragment.LOAD_DATA to null)
        data?.let {
            repository.loadEpisode(it.toString())
                .subscribe(
                    {
                        dataEpisode = it
                        _data.postValue(dataEpisode)
                        _state.postValue(StateFragment.SHOW_DATA to null)
                    },
                    {
                        _state.postValue(StateFragment.ERROR_LOAD to it.localizedMessage)
                    }
                )
        }

    }
}