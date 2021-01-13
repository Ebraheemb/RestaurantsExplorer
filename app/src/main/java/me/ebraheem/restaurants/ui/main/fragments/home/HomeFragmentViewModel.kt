package me.ebraheem.restaurants.ui.main.fragments.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.ebraheem.restaurants.data.DataRepository
import me.ebraheem.restaurants.data.model.HomePageData
import me.ebraheem.restaurants.data.model.LocationDetails
import me.ebraheem.restaurants.data.result.Result
import me.ebraheem.restaurants.helpers.plus
import me.ebraheem.restaurants.ui.base.BaseViewModel
import javax.inject.Inject

class HomeFragmentViewModel @ViewModelInject constructor(private var dataRepository: DataRepository) :
    BaseViewModel() {

    var homePageLiveData: MutableLiveData<Result<HomePageData>> = MutableLiveData()

    fun loadCity(entityId: String, entityType: String) {
        viewModelScope.launch {
            dataRepository
                .loadHomePage(entityId, entityType)
                .collect {
                    homePageLiveData.postValue(it)
                }
        }
    }
}