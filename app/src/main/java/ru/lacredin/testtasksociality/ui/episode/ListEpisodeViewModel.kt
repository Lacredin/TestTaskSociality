package ru.lacredin.testtasksociality.ui.episode

import io.reactivex.rxjava3.core.Single
import ru.lacredin.testtasksociality.App
import ru.lacredin.testtasksociality.models.ResultListModel
import ru.lacredin.testtasksociality.models.episodes.EpisodeModel
import ru.lacredin.testtasksociality.repository.Repository
import ru.lacredin.testtasksociality.ui.items.BaseItem
import ru.lacredin.testtasksociality.ui.items.EpisodeItem
import ru.lacredin.testtasksociality.ui.viewModels.BaseListViewModel
import javax.inject.Inject

class ListEpisodeViewModel @Inject constructor() : BaseListViewModel<EpisodeModel>() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var repository: Repository

    override fun getBaseRequest(): Single<ResultListModel<EpisodeModel>> {
        return repository.loadAllEpisode()
    }

    override fun getPageRequest(pageUrl: String): Single<ResultListModel<EpisodeModel>> {
        return repository.loadPageEpisode(pageUrl)
    }

    override fun createItems(data: List<EpisodeModel>): List<BaseItem> {
        val items = mutableListOf<EpisodeItem>()
        data.forEach {
            items.add(EpisodeItem(it, ::openDetailEpisode))
        }
        return items
    }

    fun openDetailEpisode(item: EpisodeItem) {
        openItemFragment.postValue(item.data.id)
    }
}