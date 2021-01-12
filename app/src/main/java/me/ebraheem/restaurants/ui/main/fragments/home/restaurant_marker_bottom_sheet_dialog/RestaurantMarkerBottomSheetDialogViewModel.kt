package me.ebraheem.restaurants.ui.main.fragments.home.restaurant_marker_bottom_sheet_dialog

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import me.ebraheem.restaurants.data.DataRepository
import me.ebraheem.restaurants.data.model.Restaurant
import me.ebraheem.restaurants.helpers.plus
import me.ebraheem.restaurants.ui.base.BaseViewModel
import javax.inject.Inject

class RestaurantMarkerBottomSheetDialogViewModel @ViewModelInject constructor(private var dataRepository: DataRepository) :BaseViewModel() {


    val restaurantLiveData : MutableLiveData<Restaurant> = MutableLiveData()


    fun loadRestaurant(resId :String){
        loadingDataLiveData.postValue(true)
        compositeDisposable + dataRepository.restaurant(resId).subscribe(
            {
                restaurantLiveData.postValue(it)
                loadingDataLiveData.postValue(false)
            },{
                loadingDataLiveData.postValue(false)
            }
        )

    }


}