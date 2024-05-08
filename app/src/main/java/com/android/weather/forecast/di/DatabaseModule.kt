package com.android.weather.forecast.di

import android.app.Application
import com.android.weather.forecast.data.data.local.sharedprefs.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSharedPreferencesManager(
        application: Application,
        @Named(InjectionConstants.KEY_PREFERENCES_NAME) name: String?
    ): SharedPreferencesManager {
        return SharedPreferencesManager(application.applicationContext, name)
    }
}