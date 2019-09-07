package me.ebraheem.restaurants.data.model


import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class City(

    @field:SerializedName("id")
    var id: Long = 0,

    @field:SerializedName("name")
    var name: String? = null,

    @field:SerializedName("country_id")
    var countryId: Long = 0,

    @field:SerializedName("country_name")
    var countryName: String? = null,

    @field:SerializedName("country_flag_url")
    var countryFlagUrl: String? = null,

    @field:SerializedName("should_experiment_with")
    var shouldExperimentWith: Long = 0,

    @field:SerializedName("discovery_enabled")
    var discoveryEnabled: Long = 0,

    @field:SerializedName("has_new_ad_format")
    var hasNewAdFormat: Long = 0,

    @field:SerializedName("is_state")
    var isState: Long = 0,

    @field:SerializedName("state_id")
    var stateId: Long = 0,

    @field:SerializedName("state_name")
    var stateName: String? = null,

    @field:SerializedName("state_code")
    var stateCode: String? = null


) : Parcelable