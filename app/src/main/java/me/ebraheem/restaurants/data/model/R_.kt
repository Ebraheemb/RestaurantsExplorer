package me.ebraheem.restaurants.data.model

import com.google.gson.annotations.SerializedName

data class R_(

    @field:SerializedName("has_menu_status")
    var hasMenuStatus: HasMenuStatus? = null,

    @field:SerializedName("res_id")
    var resId: Long = 0

)

