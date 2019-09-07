package me.ebraheem.restaurants.data.network

import me.ebraheem.restaurants.common.Constants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        request = request.newBuilder()
            .header("user-key",Constants.ZAMATO_API_KEY)
            .header("Accept","application/json")
            .build()

        return chain.proceed(request)
    }
}
