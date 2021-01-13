package me.ebraheem.restaurants.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import me.ebraheem.restaurants.App
import me.ebraheem.restaurants.data.AppDataRepository
import me.ebraheem.restaurants.data.DataRepository
import me.ebraheem.restaurants.data.network.Routes
import me.ebraheem.restaurants.data.preferences.AppPreferences
import me.ebraheem.restaurants.data.preferences.AppSharedPreferences
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideExecutor() : Executor = Executors.newFixedThreadPool(2)


    @Singleton
    @Provides
    fun provideAppDataRepository(@ApplicationContext context: Context, apiService: Routes): DataRepository =
        AppDataRepository(context as App, apiService)


    @Singleton
    @Provides
    fun provideAppPreferences(@ApplicationContext context: Context,gson: Gson): AppSharedPreferences =
        AppPreferences(context as App, gson)

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @ApplicationScope
    @Singleton
    @Provides
    fun providesApplicationScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

}