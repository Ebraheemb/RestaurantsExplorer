package me.ebraheem.restaurants.ui.main


import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.ebraheem.restaurants.data.DataRepository
import me.ebraheem.restaurants.data.model.LocationDetails
import me.ebraheem.restaurants.helpers.plus
import me.ebraheem.restaurants.ui.base.BaseViewModel
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(private var dataRepository: DataRepository) :
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
        compositeDisposable + dataRepository
            .locationDetails(entityId, entityType)
            .subscribe({
                locationDetailLiveData.postValue(it)
            }, {
                it.cause
            })
    }


}