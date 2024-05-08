package com.android.weather.forecast.data.data.remote.source

import com.android.weather.forecast.domain.model.WeatherItem

interface RemoteDataSource {
    suspend fun getWeatherForecastData(
        city: String
    ): Result<List<WeatherItem>>
}