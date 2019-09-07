package me.ebraheem.restaurants.ui.main.fragments.home.restaurant_marker_bottom_sheet_dialog


import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import me.ebraheem.restaurants.di.ChildFragmentScope
import me.ebraheem.restaurants.di.ViewModelKey

@Module
abstract class RestInfoBottomSheetModule {


    @ChildFragmentScope
    @ContributesAndroidInjector
    abstract fun contributeRestaurantMarkerDialogFragment(): RestaurantMarkerDialogFragment



    @Binds
    @IntoMap
    @ViewModelKey(RestaurantMarkerBottomSheetDialogViewModel::class)
    abstract fun bindRestaurantMarkerBottomSheetDialogViewModel(viewModel: RestaurantMarkerBottomSheetDialogViewModel): ViewModel

}