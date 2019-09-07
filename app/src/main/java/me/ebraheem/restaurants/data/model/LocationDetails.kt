package me.ebraheem.restaurants.data.model

import com.google.gson.annotations.SerializedName
import io.reactivex.Single

data class LocationDetails(

    @field:SerializedName("popularity")
    var popularity: String? = null,

    @field:SerializedName("nightlife_index")
    var nightlifeIndex: String? = null,

    @field:SerializedName("nearby_res")
    var nearbyRes: List<String>? = null,

    @field:SerializedName("top_cuisines")
    var topCuisines: List<String>? = null,

    @field:SerializedName("popularity_res")
    var popularityRes: String? = null,

    @field:SerializedName("nightlife_res")
    var nightlifeRes: String? = null,

    @field:SerializedName("subzone")
    var subzone: String? = null,

    @field:SerializedName("subzone_id")
    var subzoneId: Long = 0,

    @SerializedName("city")
    var city: String? = null,

    @field:SerializedName("location")
    var location: Location? = null,

    @field:SerializedName("num_restaurant")
    var numRestaurant: Long = 0,

    @field:SerializedName("best_rated_restaurant")
    var bestRatedRestaurant: List<RestaurantWrapper>? = null

)