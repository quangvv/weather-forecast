package com.android.weather.forecast.domain.model

data class WeatherItem(
    val weatherInfo: WeatherInfo,
    val weather: List<Weather>,
    val cloud: Int,
    val windSpeed: Double,
    val dateText: String,
    val cityName:String,
    val lat: Double,
    val lon: Double
)

data class WeatherInfo(
    val temperature: Double,
    val pressure: Int,
    val humidity: Int,
)

data class Weather(
    val id: Int,
    val description: String,
    val icon: String
)