package com.android.weather.forecast.domain.usecase

import com.android.weather.forecast.data.model.Resource
import com.android.weather.forecast.domain.model.WeatherItem
import com.android.weather.forecast.domain.repository.WeatherForecastRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherListUseCase @Inject constructor(private val weatherRepository: WeatherForecastRepository) {

    suspend fun getWeatherForecast(city: String): Flow<Resource<List<WeatherItem>>> {
        return weatherRepository.getWeatherForecast(city)
    }
}