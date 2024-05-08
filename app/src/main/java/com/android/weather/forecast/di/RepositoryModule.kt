package com.android.weather.forecast.di

import android.content.Context
import com.android.weather.forecast.common.connectivity.ConnectivityDetector
import com.android.weather.forecast.common.dispatcher.DispatcherProvider
import com.android.weather.forecast.data.repository.WeatherForecastRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.android.weather.forecast.data.data.local.sharedprefs.source.PreferenceSource
import com.android.weather.forecast.data.data.remote.source.RemoteDataSource
import com.android.weather.forecast.domain.repository.WeatherForecastRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherForecastRepository(
        context: Context,
        connect: ConnectivityDetector,
        dispatcherProvider: DispatcherProvider,
        remoteDataSource: RemoteDataSource,
        localDataSource: PreferenceSource
    ): WeatherForecastRepository {
        return WeatherForecastRepositoryImpl(
            context,
            connect,
            dispatcherProvider,
            remoteDataSource,
            localDataSource
        )
    }
}