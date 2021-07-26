package ru.lacredin.testtasksociality.models.episodes


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeModel(
    @Json(name = "air_date")
    var airDate: String? = null,
    @Json(name = "characters")
    var characters: List<Any>? = null,
    @Json(name = "created")
    var created: String? = null,
    @Json(name = "episode")
    var episode: String? = null,
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "url")
    var url: String? = null
)