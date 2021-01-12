package me.ebraheem.restaurants.data.model

import com.google.gson.annotations.SerializedName

data class ReviewWrapper(

    @field:SerializedName("review")
    var review: List<Review>? = null

)