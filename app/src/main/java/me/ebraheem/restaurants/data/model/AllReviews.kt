package me.ebraheem.restaurants.data.model

import com.google.gson.annotations.SerializedName

data class AllReviews(

    @field:SerializedName("reviews")
    var reviews: List<ReviewWrapper>? = null

)