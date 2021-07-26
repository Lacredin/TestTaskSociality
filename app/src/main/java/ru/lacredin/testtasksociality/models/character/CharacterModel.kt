package ru.lacredin.testtasksociality.models.character


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterModel(
    @Json(name = "created")
    var created: String? = null,
    @Json(name = "episode")
    var episode: List<Any>? = null,
    @Json(name = "gender")
    var gender: String? = null,
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "image")
    var image: String? = null,
    @Json(name = "location")
    var location: Location? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "origin")
    var origin: Origin? = null,
    @Json(name = "species")
    var species: String? = null,
    @Json(name = "status")
    var status: String? = null,
    @Json(name = "type")
    var type: String? = null,
    @Json(name = "url")
    var url: String? = null
)