package me.ebraheem.restaurants.ui.main

import androidx.lifecycle.MutableLiveData
import me.ebraheem.restaurants.data.model.City
import me.ebraheem.restaurants.data.model.LocationDetails

interface MainActivityDelegate {

    fun onNewCitySelected(city: City)

    fun onChangeSelectedCity(city: City)

    fun getLocationDetails() : MutableLiveData<LocationDetails>

}
