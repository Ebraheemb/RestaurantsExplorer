package me.ebraheem.restaurants.ui.restaurant

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import me.ebraheem.restaurants.data.DataRepository
import me.ebraheem.restaurants.data.model.Restaurant
import me.ebraheem.restaurants.data.model.Review
import me.ebraheem.restaurants.data.result.Result
import me.ebraheem.restaurants.ui.base.BaseViewModel
import java.lang.Exception

class RestaurantActivityViewModel @ViewModelInject constructor(private var dataRepository: DataRepository) : BaseViewModel() {

    fun loadRestaurant(resId: String) : LiveData<Result<RestaurantActivityData>> {
        val liveData = MutableLiveData<Result<RestaurantActivityData>>()
        liveData.postValue(Result.Loading)
        viewModelScope.launch {
            flowOf(dataRepository.restaurant(resId)).zip(flowOf(dataRepository.restaurantReviews(resId))){
                    restaurantResult: Result<Restaurant>, reviewsResult: Result<List<Review>> ->
                val value : Result<RestaurantActivityData>
                if(restaurantResult is Result.Success && reviewsResult is Result.Success){
                    value = Result.Success(RestaurantActivityData(restaurantResult.data,reviewsResult.data))
                } else {
                     value = Result.Error(Exception("error"))
                }
                value
            }.collect {
                liveData.postValue(it)
            }
        }

        return liveData
    }

}

data class RestaurantActivityData(var restaurant: Restaurant,var reviews : List<Review>)