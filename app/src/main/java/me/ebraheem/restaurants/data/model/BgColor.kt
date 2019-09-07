package me.ebraheem.restaurants.data.model

import com.google.gson.annotations.SerializedName

data class BgColor(

    @field:SerializedName("type")
    var type: String? = null,
    @field:SerializedName("tint")
    var tint: String? = null

)