package me.ebraheem.restaurants.ui.restaurant

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.ebraheem.restaurants.data.DataRepository
import me.ebraheem.restaurants.data.model.Restaurant
import me.ebraheem.restaurants.helpers.plus
import me.ebraheem.restaurants.ui.base.BaseViewModel
import javax.inject.Inject

class RestaurantActivityViewModel @Inject constructor(private var dataRepository: DataRepository) : BaseViewModel() {

    var restaurantLiveData: MutableLiveData<Restaurant> = MutableLiveData()

    fun loadRestaurant(resId: String) {
        loadingDataLiveData.postValue(true)
        compositeDisposable + dataRepository
            .restaurant(resId)
            .subscribe({
                loadingDataLiveData.postValue(false)
                restaurantLiveData.postValue(it!!)
            }, {
                loadingDataLiveData.postValue(false)
                it.cause
            })
    }
}