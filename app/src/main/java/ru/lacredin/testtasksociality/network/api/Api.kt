package ru.lacredin.testtasksociality.network.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import ru.lacredin.testtasksociality.models.LocationsItem
import ru.lacredin.testtasksociality.models.LocationsModel

interface Api {

    @GET("location/{id}")
    fun getLocation(@Path("id") id: String?): Single<LocationsItem>

    @GET("location")
    fun getLocations(): Single<LocationsModel>

    @GET
    fun <T> getPage(@Url url: String): Single<T>
}