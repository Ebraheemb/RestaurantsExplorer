package me.ebraheem.restaurants.data.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import me.ebraheem.restaurants.App
import me.ebraheem.restaurants.common.Constants
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideCache(cacheFile: File): Cache = Cache(cacheFile, Constants.OKHTTP_CACHE_DIR_SIZE)

    @Provides
    fun provideHttpCacheFile(@ApplicationContext context: Context): File {
        val directory = File(context.cacheDir.toString() + File.separator + Constants.OKHTTP_CACHE_DIR_NAME)
        if (!directory.exists())
            directory.mkdirs()
        return directory

    }

    @Singleton
    @Provides
    fun provideOkHttpClient(cache: Cache, @Named("AuthInterceptor") authInterceptor: Interceptor, @Named("ChuckInterceptor") chuckInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(chuckInterceptor)
            .addInterceptor(authInterceptor)
            .cache(cache)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()


    @Provides
    @Named("AuthInterceptor")
    fun provideAuthInterceptor(): Interceptor =
        AuthInterceptor()

    @Provides
    @Named("ChuckInterceptor")
    fun provideChuckInterceptor(@ApplicationContext context: Context): Interceptor =
        ChuckInterceptor(context)

    @Provides
    fun RxJava2CallAdapterFactory() = RxJava2CallAdapterFactory.create()

    @Singleton
    @Provides
    fun getClient(
        okHttpClient: OkHttpClient,
        gson: Gson,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): Routes = retrofit.create(Routes::class.java)
}