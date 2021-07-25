package ru.lacredin.testtasksociality.repository

import ru.lacredin.testtasksociality.extensions.standardThread
import ru.lacredin.testtasksociality.network.api.Api
import ru.lacredin.testtasksociality.network.api.RestProvider

class Repository(
    var api: Api = RestProvider.restAPI
) {

    fun loadAllLocation() = api.getLocations()
        .standardThread()

    fun loadPageLocation(url: String) = api.getLocations(url)
        .standardThread()
}