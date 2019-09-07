package me.ebraheem.restaurants.data.model

import com.google.gson.annotations.SerializedName

data class Review (

    @field:SerializedName("rating")
    var rating: Double = 0.0,

    @field:SerializedName("review_text")
    var reviewText: String? = null,

    @field:SerializedName("id")
    var id: Long = 0,

    @field:SerializedName("rating_color")
    var ratingColor: String? = null,

    @field:SerializedName("review_time_friendly")
    var reviewTimeFriendly: String? = null,

    @field:SerializedName("rating_text")
    var ratingText: String? = null,

    @field:SerializedName("timestamp")
    var timestamp: Long = 0,

    @field:SerializedName("likes")
    var likes: Long = 0,

    @field:SerializedName("user")
    var user: User? = null,

    @field:SerializedName("comments_count")
    var commentsCount: Long = 0

)