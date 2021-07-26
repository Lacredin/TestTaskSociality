package ru.lacredin.testtasksociality.ui.personage

import io.reactivex.rxjava3.core.Single
import ru.lacredin.testtasksociality.App
import ru.lacredin.testtasksociality.models.ResultListModel
import ru.lacredin.testtasksociality.models.character.CharacterModel
import ru.lacredin.testtasksociality.repository.Repository
import ru.lacredin.testtasksociality.ui.items.BaseItem
import ru.lacredin.testtasksociality.ui.items.CharacterItem
import ru.lacredin.testtasksociality.ui.viewModels.BaseListViewModel
import javax.inject.Inject

class PersonageViewModel @Inject constructor() : BaseListViewModel<CharacterModel>() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var repository: Repository

    override fun getBaseRequest(): Single<ResultListModel<CharacterModel>> {
        return repository.loadAllCharacter()
    }

    override fun getPageRequest(pageUrl: String): Single<ResultListModel<CharacterModel>> {
        return repository.loadPageCharacter(pageUrl)
    }

    override fun createItems(data: List<CharacterModel>): List<BaseItem> {
        val items = mutableListOf<CharacterItem>()
        data.forEach {
            items.add(CharacterItem(it, ::openDetailEpisode))
        }
        return items
    }

    fun openDetailEpisode(item: CharacterItem) {
        openItemFragment.postValue(item.data.id)
    }
}