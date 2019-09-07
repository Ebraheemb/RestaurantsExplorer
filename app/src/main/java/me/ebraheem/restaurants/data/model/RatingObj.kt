package me.ebraheem.restaurants.data.model

import com.google.gson.annotations.SerializedName

data class RatingObj(

    @SerializedName("title")
    var title: Title? = null,
    @SerializedName("bg_color")
    var bgColor: BgColor? = null

)