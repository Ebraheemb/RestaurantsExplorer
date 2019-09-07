package me.ebraheem.restaurants.data.model

import com.google.gson.annotations.SerializedName

data class User(

    @field:SerializedName("name")
    var name: String? = null,

    @field:SerializedName("zomato_handle")
    var zomatoHandle: String? = null,

    @field:SerializedName("foodie_level")
    var foodieLevel: String? = null,

    @field:SerializedName("foodie_level_num")
    var foodieLevelNum: Long = 0,

    @field:SerializedName("foodie_color")
    var foodieColor: String? = null,

    @field:SerializedName("profile_url")
    var profileUrl: String? = null,

    @field:SerializedName("profile_image")
    var profileImage: String? = null,

    @field:SerializedName("profile_deeplink")
    var profileDeeplink: String? = null

)