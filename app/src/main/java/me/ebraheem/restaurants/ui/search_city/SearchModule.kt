package me.ebraheem.restaurants.ui.search_city

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.ebraheem.restaurants.di.ViewModelKey

@Module
abstract class SearchModule {


    @Binds
    @IntoMap
    @ViewModelKey(SearchCityViewModel::class)
    abstract fun bindSearchCityViewModel(viewModel: SearchCityViewModel): ViewModel


}