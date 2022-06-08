package dev.anand.synchronossweatherapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.anand.synchronossweatherapp.BuildConfig
import dev.anand.synchronossweatherapp.network.CurrentWeatherService
import dev.anand.synchronossweatherapp.network.QueryParameterAddInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.LoggingEventListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Network {
    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return  OkHttpClient.Builder()
            .apply { if (BuildConfig.DEBUG) eventListenerFactory(LoggingEventListener.Factory()) }
            .addInterceptor(QueryParameterAddInterceptor())
            .addInterceptor(getLoggingInterceptor())
            .build()

    }
    private fun getLoggingInterceptor(): Interceptor {

        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.WEATHER_APP_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): CurrentWeatherService{
            return  retrofit.create(CurrentWeatherService::class.java)
    }


}