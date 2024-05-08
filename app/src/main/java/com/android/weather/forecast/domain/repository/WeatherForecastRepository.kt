package com.android.weather.forecast.domain.repository

import com.android.weather.forecast.data.model.Resource
import com.android.weather.forecast.domain.model.WeatherItem
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRepository {
    suspend fun getWeatherForecast(city: String): Flow<Resource<List<WeatherItem>>>
}