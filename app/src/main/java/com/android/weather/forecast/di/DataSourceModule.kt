package com.android.weather.forecast.di

import com.android.weather.forecast.data.data.local.sharedprefs.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.android.weather.forecast.data.data.local.sharedprefs.source.PreferenceSource
import com.android.weather.forecast.data.data.local.sharedprefs.source.PreferenceSourceImp
import com.android.weather.forecast.data.data.remote.WeatherForecastService
import com.android.weather.forecast.data.data.remote.source.RemoteDataSourceImp
import com.android.weather.forecast.data.data.remote.source.RemoteDataSource
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    //------------------------------------Local data source---------------------------------

    @Provides
    @Singleton
    fun provideLocalDataSource(
        sharedPreferences: SharedPreferencesManager
    ): PreferenceSource {
        return PreferenceSourceImp(sharedPreferences)
    }

    //------------------------------------Remote data source--------------------------------
    @Provides
    @Singleton
    fun provideRemoteDataSource(
        @Named(InjectionConstants.KEY_API_KEY) apiKey: String,
        apiService: WeatherForecastService
    ): RemoteDataSource {
        return RemoteDataSourceImp(apiKey, apiService)
    }
}