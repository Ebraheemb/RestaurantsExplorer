package me.ebraheem.restaurants.data

import dagger.Module
import dagger.Provides
import me.ebraheem.restaurants.App
import me.ebraheem.restaurants.data.network.NetworkModule
import me.ebraheem.restaurants.data.network.Routes
import me.ebraheem.restaurants.data.preferences.PreferencesModule
import me.ebraheem.restaurants.di.AppModule
import me.ebraheem.restaurants.di.AppScope

@Module(includes = [AppModule::class, NetworkModule::class, PreferencesModule::class])
class DataModule {

    @AppScope
    @Provides
    fun provideAppDataRepository(app: App, apiService: Routes): DataRepository =
        AppDataRepository(app, apiService)


}