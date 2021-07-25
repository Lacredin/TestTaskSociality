package ru.lacredin.testtasksociality.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationsItem(
    @Json(name = "created")
    var created: String? = null,
    @Json(name = "dimension")
    var dimension: String? = null,
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "residents")
    var residents: List<Any>? = null,
    @Json(name = "type")
    var type: String? = null,
    @Json(name = "url")
    var url: String? = null
)