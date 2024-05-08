package com.android.weather.forecast.di

import com.android.weather.forecast.common.dispatcher.DispatcherProvider
import com.android.weather.forecast.common.dispatcher.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DispatcherModule {

    @Binds
    fun bindsDispatcherProvider(dispatcherProviderImpl: DispatcherProviderImpl): DispatcherProvider
}