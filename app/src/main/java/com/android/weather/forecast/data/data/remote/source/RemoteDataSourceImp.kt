package com.android.weather.forecast.data.data.remote.source

import com.android.weather.forecast.data.data.mappers.toEntity
import com.android.weather.forecast.data.data.remote.WeatherForecastService
import com.android.weather.forecast.data.model.RequestException
import com.android.weather.forecast.domain.model.WeatherItem
import java.net.HttpURLConnection
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiKey: String,
    private val apiService: WeatherForecastService
) :
    RemoteDataSource {
    override suspend fun getWeatherForecastData(
        city: String
    ): Result<List<WeatherItem>> {
        return try {
            val apiResponse = apiService.getWeatherForecastData(
                city = city,
                cnt = 7,
                apiKey = apiKey
            )
            if (apiResponse.isSuccessful && apiResponse.code() == 200) {
                var weatherItemList: List<WeatherItem> = emptyList()
                apiResponse.body()?.let { responseBody ->
                    responseBody.let {
                        val weatherItems = it.toEntity()
                        weatherItemList =
                            weatherItems.distinctBy { weatherItem -> weatherItem.dateText }
                    }
                }
                Result.success(weatherItemList)
            } else {
                val msg = apiResponse.message()
                Result.failure(
                    RequestException(
                        code = apiResponse.code(),
                        message = msg
                    )
                )
            }
        } catch (ex: Exception) {
            Result.failure(
                RequestException(
                    code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                    message = "An error occurred!"
                )
            )
        }
    }
}