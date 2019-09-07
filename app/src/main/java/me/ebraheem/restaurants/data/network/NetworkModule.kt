package me.ebraheem.restaurants.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import me.ebraheem.restaurants.App
import me.ebraheem.restaurants.common.Constants
import me.ebraheem.restaurants.di.AppModule
import me.ebraheem.restaurants.di.AppScope
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named


@Module(includes = [AppModule::class])
class NetworkModule {


    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    fun provideCache(cacheFile: File): Cache = Cache(cacheFile, Constants.OKHTTP_CACHE_DIR_SIZE)

    @Provides
    fun provideHttpCacheFile(app: App): File {
        val directory = File(app.cacheDir.toString() + File.separator + Constants.OKHTTP_CACHE_DIR_NAME)
        if (!directory.exists())
            directory.mkdirs()
        return directory

    }

    @AppScope
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
    fun provideChuckInterceptor(app: App): Interceptor =
        ChuckInterceptor(app.applicationContext)

    @Provides
    fun RxJava2CallAdapterFactory() = RxJava2CallAdapterFactory.create()

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


    @AppScope
    @Provides
    fun provideApiService(retrofit: Retrofit): Routes = retrofit.create(Routes::class.java)
}