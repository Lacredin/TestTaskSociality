package ru.lacredin.testtasksociality.di.components

import dagger.Component
import ru.lacredin.testtasksociality.repository.Repository
import ru.lacredin.testtasksociality.ui.locations.DetailLocationFragment
import ru.lacredin.testtasksociality.ui.locations.DetailLocationViewModel
import ru.lacredin.testtasksociality.ui.locations.LocationsFragment
import ru.lacredin.testtasksociality.ui.locations.LocationsViewModel

@Component
interface ApplicationComponent {
    fun inject(viewModel: DetailLocationViewModel)
    fun inject(viewModel: LocationsViewModel)
    fun inject(repository: Repository)
    fun inject(fragment: DetailLocationFragment)
    fun inject(fragment: LocationsFragment)
}