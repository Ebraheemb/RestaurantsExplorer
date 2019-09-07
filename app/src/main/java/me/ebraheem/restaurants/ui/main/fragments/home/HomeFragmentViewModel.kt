package me.ebraheem.restaurants.ui.main.fragments.home

import androidx.lifecycle.MutableLiveData
import me.ebraheem.restaurants.data.DataRepository
import me.ebraheem.restaurants.data.model.HomePageData
import me.ebraheem.restaurants.data.model.LocationDetails
import me.ebraheem.restaurants.helpers.plus
import me.ebraheem.restaurants.ui.base.BaseViewModel
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(private var dataRepository: DataRepository) :
    BaseViewModel() {


    var homePageLiveData: MutableLiveData<HomePageData> = MutableLiveData()


    fun loadCity(entityId: String, entityType: String) {
        loadingDataLiveData.postValue(true)

        compositeDisposable + dataRepository
            .loadHomePage(entityId, entityType)
            .subscribe(
                {
                    loadingDataLiveData.postValue(false)
                    homePageLiveData.postValue(it)
                }, {
                    it.cause
                    loadingDataLiveData.postValue(false)
                }
            )
    }

}