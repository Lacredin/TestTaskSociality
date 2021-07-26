package ru.lacredin.testtasksociality.models.character


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "url")
    var url: String? = null
)