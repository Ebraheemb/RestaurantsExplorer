package me.ebraheem.restaurants.data.model

import com.google.gson.annotations.SerializedName

data class LocationGeneralResponse<T>(

    @SerializedName("status")
    var staus: String,
    @field:SerializedName("has_more")
    var hasMore: Long = 0,

    @field:SerializedName("has_total")
    var hasTotal: Long = 0,

    @field:SerializedName("location_suggestions")
    var locationSuggestions: List<T>

)
