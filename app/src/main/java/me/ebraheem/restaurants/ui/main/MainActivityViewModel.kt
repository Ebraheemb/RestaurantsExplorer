package me.ebraheem.restaurants.ui.main


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.ebraheem.restaurants.data.DataRepository
import me.ebraheem.restaurants.data.model.LocationDetails
import me.ebraheem.restaurants.data.result.Result
import me.ebraheem.restaurants.helpers.plus
import me.ebraheem.restaurants.ui.base.BaseViewModel
import javax.inject.Inject


class MainActivityViewModel @ViewModelInject constructor(private var dataRepository: DataRepository) :
    BaseViewModel() {


    var locationDetailLiveData: MutableLiveData<LocationDetails> = MutableLiveData()


    fun getLocationDetails(entityId: String, entityType: String): MutableLiveData<LocationDetails> {
        locationDetailLiveData.value?.let {
            return locationDetailLiveData
        }
        loadCity(entityId, entityType)

        return locationDetailLiveData
    }


    fun loadCity(entityId: String, entityType: String) {
        viewModelScope.launch {
            dataRepository
                .locationDetails(entityId, entityType)
                .collect {
                    if (it is Result.Success)
                    locationDetailLiveData.postValue(it.data)

                }
        }

    }


}