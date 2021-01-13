package me.ebraheem.restaurants.ui.search_city


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.ebraheem.restaurants.data.DataRepository
import me.ebraheem.restaurants.data.model.City
import me.ebraheem.restaurants.data.result.Result
import me.ebraheem.restaurants.helpers.plus
import me.ebraheem.restaurants.ui.base.BaseViewModel

import javax.inject.Inject

class SearchCityViewModel @ViewModelInject constructor(private var dataRepository: DataRepository) : BaseViewModel() {

    var citiesListLiveData: MutableLiveData<Result<List<City>>> = MutableLiveData()

    fun searchFor(query: String) {
        viewModelScope.launch {
            dataRepository.searchCity(query)
                .collect {
                    citiesListLiveData.postValue(it)
                }
        }
    }
}