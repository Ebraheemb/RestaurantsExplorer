package me.ebraheem.restaurants.data.model


import com.google.gson.annotations.SerializedName

data class Location(

    @field:SerializedName("entity_type")
    var entityType: String? = null,

    @field:SerializedName("entity_id")
    var entityId: Long = 0,

    @field:SerializedName("title")
    var title: String? = null,

    @field:SerializedName("latitude")
    var latitude: Double = 0.toDouble(),

    @field:SerializedName("longitude")
    var longitude: Double = 0.toDouble(),

    @field:SerializedName("city_id")
    var cityId: Long = 0,

    @field:SerializedName("city_name")
    var cityName: String? = null,

    @field:SerializedName("country_id")
    var countryId: Long = 0,

    @field:SerializedName("country_name")
    var countryName: String? = null

)