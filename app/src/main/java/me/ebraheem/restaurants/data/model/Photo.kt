package me.ebraheem.restaurants.data.model

import com.google.gson.annotations.SerializedName

data class Photo(

    @field:SerializedName("id")
    var id: String? = null,

    @field:SerializedName("url")
    var url: String? = null,

    @field:SerializedName("thumb_url")
    var thumbUrl: String? = null,

    @field:SerializedName("user")
    var user: User? = null,

    @field:SerializedName("res_id")
    var resId: Long = 0,

    @field:SerializedName("caption")
    var caption: String? = null,

    @field:SerializedName("timestamp")
    var timestamp: Long = 0,

    @field:SerializedName("friendly_time")
    var friendlyTime: String? = null,

    @field:SerializedName("width")
    var width: Long = 0,

    @field:SerializedName("height")
    var height: Long = 0

)