package com.android.weather.forecast.di

import com.android.weather.forecast.common.connectivity.ConnectivityDetector
import com.android.weather.forecast.common.connectivity.ConnectivityDetectorImp
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ConnectivityModule {
    @Binds
    @Reusable
    fun bindsConnectivityDetectorImpl(
        connectivityDetectorImp: ConnectivityDetectorImp
    ): ConnectivityDetector
}