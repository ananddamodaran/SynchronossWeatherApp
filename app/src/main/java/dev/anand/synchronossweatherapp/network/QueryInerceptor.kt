package dev.anand.synchronossweatherapp.network

import dev.anand.synchronossweatherapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class QueryParameterAddInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain.request().url.newBuilder()
            .addQueryParameter("appid", BuildConfig.WEATHER_APP_ID)
            .build()
        val request = chain.request().newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}