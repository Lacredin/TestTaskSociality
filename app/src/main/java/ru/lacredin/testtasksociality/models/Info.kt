package ru.lacredin.testtasksociality.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Info(
    @Json(name = "count")
    var count: Int? = null,
    @Json(name = "next")
    var next: String? = null,
    @Json(name = "pages")
    var pages: Int? = null,
    @Json(name = "prev")
    var prev: String? = null
)