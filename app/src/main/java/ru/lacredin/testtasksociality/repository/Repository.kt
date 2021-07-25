package ru.lacredin.testtasksociality.repository

import ru.lacredin.testtasksociality.App
import ru.lacredin.testtasksociality.extensions.standardThread
import ru.lacredin.testtasksociality.models.LocationsModel
import ru.lacredin.testtasksociality.network.api.RestProvider
import javax.inject.Inject

class Repository @Inject constructor() {
    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var restProvider: RestProvider

    fun loadLocation(id: String? = null) = restProvider.restAPI.getLocation(id)
        .standardThread()

    fun loadAllLocation() = restProvider.restAPI.getLocations()
        .standardThread()

    fun loadPageLocation(url: String) = restProvider.restAPI.getPage<LocationsModel>(url)
        .standardThread()
}