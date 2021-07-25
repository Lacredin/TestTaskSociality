package ru.lacredin.testtasksociality.network.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.lacredin.testtasksociality.models.LocationsModel

interface Api {

    @GET("location")
    fun getLocations(): Single<LocationsModel>

    @GET
    fun getLocations(@Url url: String): Single<LocationsModel>
}