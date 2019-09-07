package me.ebraheem.restaurants.ui.search_city


import androidx.lifecycle.MutableLiveData
import me.ebraheem.restaurants.data.DataRepository
import me.ebraheem.restaurants.data.model.City
import me.ebraheem.restaurants.helpers.plus
import me.ebraheem.restaurants.ui.base.BaseViewModel

import javax.inject.Inject

class SearchCityViewModel @Inject constructor(private var dataRepository: DataRepository) : BaseViewModel() {


    var citiesListLiveData: MutableLiveData<List<City>> = MutableLiveData()


    fun searchFor(query: String) {
        loadingDataLiveData.postValue(true)

        compositeDisposable + dataRepository.searchCity(query)
            .subscribe {
                citiesListLiveData.postValue(it)
                loadingDataLiveData.postValue(false)
            }
    }

    fun citiesListLiveData() = citiesListLiveData
}