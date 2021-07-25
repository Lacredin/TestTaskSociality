package ru.lacredin.testtasksociality.repository

import ru.lacredin.testtasksociality.extensions.standardThread
import ru.lacredin.testtasksociality.models.LocationsModel
import ru.lacredin.testtasksociality.network.api.Api
import ru.lacredin.testtasksociality.network.api.RestProvider

class Repository(
    protected var api: Api = RestProvider.restAPI
) {

    fun loadLocation(id: String? = null) = api.getLocation(id)
        .standardThread()

    fun loadAllLocation() = api.getLocations()
        .standardThread()

    fun loadPageLocation(url: String) = api.getPage<LocationsModel>(url)
        .standardThread()
}