package com.android.weather.forecast.di

import com.android.weather.forecast.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {

    @Provides
    @Named(InjectionConstants.KEY_API_URL)
    fun providesApiUrl(): String {
        return BuildConfig.API_URL
    }

    @Provides
    @Named(InjectionConstants.KEY_API_KEY)
    fun providesApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @Named(InjectionConstants.KEY_DATABASE_NAME)
    fun providesDatabaseName(): String {
        return BuildConfig.DATABASE_NAME
    }

    @Provides
    @Named(InjectionConstants.KEY_DATABASE_VERSION)
    fun providesDatabaseVersion(): String {
        return BuildConfig.DATABASE_VERSION
    }

    @Provides
    @Named(InjectionConstants.KEY_PREFERENCES_NAME)
    fun providesSharedPrefName(): String {
        return BuildConfig.SHARE_PREFERENCE_NAME
    }

    @Provides
    @Named(InjectionConstants.KEY_IS_DEBUG_BUILD)
    fun providesIsDebugBuild(): Boolean {
        return BuildConfig.DEBUG
    }
}