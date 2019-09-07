package me.ebraheem.restaurants.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {


    var compositeDisposable: CompositeDisposable = CompositeDisposable()
    val loadingDataLiveData: MutableLiveData<Boolean> = MutableLiveData()


    fun getLoadingLiveData() = loadingDataLiveData

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}