package ru.lacredin.testtasksociality.di.components

import dagger.Component
import ru.lacredin.testtasksociality.repository.Repository
import ru.lacredin.testtasksociality.ui.episode.DetailEpisodeFragment
import ru.lacredin.testtasksociality.ui.episode.DetailEpisodeViewModel
import ru.lacredin.testtasksociality.ui.episode.ListEpisodeFragment
import ru.lacredin.testtasksociality.ui.episode.ListEpisodeViewModel
import ru.lacredin.testtasksociality.ui.locations.DetailLocationFragment
import ru.lacredin.testtasksociality.ui.locations.DetailLocationViewModel
import ru.lacredin.testtasksociality.ui.locations.LocationsFragment
import ru.lacredin.testtasksociality.ui.locations.LocationsViewModel
import ru.lacredin.testtasksociality.ui.personage.DetailCharacterFragment
import ru.lacredin.testtasksociality.ui.personage.DetailCharacterViewModel
import ru.lacredin.testtasksociality.ui.personage.PersonageFragment
import ru.lacredin.testtasksociality.ui.personage.PersonageViewModel

@Component
interface ApplicationComponent {
    fun inject(viewModel: DetailLocationViewModel)
    fun inject(viewModel: LocationsViewModel)
    fun inject(viewModel: ListEpisodeViewModel)
    fun inject(viewModel: DetailEpisodeViewModel)
    fun inject(viewModel: PersonageViewModel)
    fun inject(viewModel: DetailCharacterViewModel)

    fun inject(repository: Repository)

    fun inject(fragment: DetailLocationFragment)
    fun inject(fragment: LocationsFragment)
    fun inject(fragment: ListEpisodeFragment)
    fun inject(fragment: DetailEpisodeFragment)
    fun inject(fragment: PersonageFragment)
    fun inject(fragment: DetailCharacterFragment)
}