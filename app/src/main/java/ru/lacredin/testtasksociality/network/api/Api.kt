package ru.lacredin.testtasksociality.network.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import ru.lacredin.testtasksociality.models.locations.LocationModel
import ru.lacredin.testtasksociality.models.ResultListModel
import ru.lacredin.testtasksociality.models.episodes.EpisodeModel

interface Api {

    @GET("location/{id}")
    fun getLocation(@Path("id") id: String?): Single<LocationModel>

    @GET("location")
    fun getLocations(): Single<ResultListModel<LocationModel>>

    @GET
    fun <T> getPageLocation(@Url url: String): Single<ResultListModel<LocationModel>>

    @GET("episode/{id}")
    fun getEpisode(@Path("id") id: String?): Single<EpisodeModel>

    @GET("episode")
    fun getEpisodes(): Single<ResultListModel<EpisodeModel>>

    @GET
    fun getPageEpisodes(@Url url: String): Single<ResultListModel<EpisodeModel>>
}