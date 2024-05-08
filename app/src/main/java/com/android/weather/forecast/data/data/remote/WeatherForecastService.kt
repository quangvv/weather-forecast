package com.android.weather.forecast.data.data.remote

import com.android.weather.forecast.data.model.response.WeatherForecastResponse
import retrofit2.Response
import retrofit2.http.*

interface WeatherForecastService {

    //@GET("forecast/daily?")
    @GET("forecast?")
    suspend fun getWeatherForecastData(
        @Query("q") city: String,
        @Query("cnt") cnt: Int,
//        @Query("units") units: String = "metric",
//        @Query("exclude") exclude: String = "minutely,hourly",
        @Query("appid") apiKey: String,
    ): Response<WeatherForecastResponse>
}