package ru.lacredin.testtasksociality.models.locations


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class LocationModel(
    @Json(name = "created")
    var created: String? = null,
    @Json(name = "dimension")
    var dimension: String? = null,
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "residents")
    var residents: List<String>? = null,
    @Json(name = "type")
    var type: String? = null,
    @Json(name = "url")
    var url: String? = null
) : Parcelable