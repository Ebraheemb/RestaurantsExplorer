package me.ebraheem.restaurants.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @field:SerializedName("results_found")
    var results_found: Long = 0,

    @field:SerializedName("results_start")
    var results_start: Long = 0,

    @field:SerializedName("results_shown")
    var results_shown: Long = 0,

    @field:SerializedName("restaurants")
    var restaurants: List<RestaurantWrapper>? = null





)