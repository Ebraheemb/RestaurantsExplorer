package me.ebraheem.restaurants.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantReviewsResponse(
    @SerializedName("user_reviews")
    var userReviews : List<ReviewWrapper> = listOf()
)