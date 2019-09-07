package me.ebraheem.restaurants.data.model

import com.google.gson.annotations.SerializedName

data class UserRating (

    @field:SerializedName("aggregate_rating")
    var aggregateRating: String? = null,

    @field:SerializedName("rating_text")
    var ratingText: String? = null,

    @SerializedName("rating_color")
    var ratingColor: String? = null,

    @field:SerializedName("rating_obj")
    var ratingObj: RatingObj? = null,

    @field:SerializedName("votes")
    var votes: String? = null

)