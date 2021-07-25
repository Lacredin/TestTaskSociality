package ru.lacredin.testtasksociality.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationsModel(
    @Json(name = "info")
    var info: Info? = null,
    @Json(name = "results")
    var results: List<LocationsItem>? = null
)