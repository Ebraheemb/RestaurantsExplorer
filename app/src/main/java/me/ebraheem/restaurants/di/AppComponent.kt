package me.ebraheem.restaurants.di


import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.ebraheem.restaurants.App
import me.ebraheem.restaurants.data.DataModule
import me.ebraheem.restaurants.data.network.NetworkModule
import me.ebraheem.restaurants.data.preferences.PreferencesModule


@AppScope
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    DataModule::class,
    ViewModelFactoryModule::class,
    PreferencesModule::class,
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {

        fun create(appModule: AppModule): AppComponent
    }

}