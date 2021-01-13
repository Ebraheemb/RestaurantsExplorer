package me.ebraheem.restaurants.ui.main.fragments.home.restaurant_marker_bottom_sheet_dialog

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import me.ebraheem.restaurants.data.DataRepository
import me.ebraheem.restaurants.ui.base.BaseViewModel

class RestaurantMarkerBottomSheetDialogViewModel @ViewModelInject constructor(private var dataRepository: DataRepository) :BaseViewModel() {

    fun loadRestaurant(resId :String) =  dataRepository.restaurantFlow(resId).asLiveData()

}