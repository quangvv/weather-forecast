package com.android.weather.forecast.di

import com.android.weather.forecast.data.data.remote.RequestInterceptor
import com.android.weather.forecast.data.data.remote.WeatherForecastService
import com.android.weather.forecast.utils.CONTENT_TYPE
import com.android.weather.forecast.utils.CONTENT_TYPE_VALUE
import com.android.weather.forecast.utils.TIMEOUT_CONNECT
import com.android.weather.forecast.utils.TIMEOUT_READ
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(
        @Named(InjectionConstants.KEY_IS_DEBUG_BUILD) debugBuild: Boolean
    ): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = if (debugBuild) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor,
    ): OkHttpClient {
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        })
        okHttpBuilder.addInterceptor(interceptor)
        okHttpBuilder.addInterceptor(RequestInterceptor())
        okHttpBuilder.connectTimeout(TIMEOUT_CONNECT.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @Named(InjectionConstants.KEY_API_URL) baseUrl: String,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideWeatherForecastService(retrofit: Retrofit): WeatherForecastService =
        retrofit.create(WeatherForecastService::class.java)
}
