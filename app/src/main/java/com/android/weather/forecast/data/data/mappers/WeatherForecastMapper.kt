package com.android.weather.forecast.data.data.mappers

import com.android.weather.forecast.data.model.response.ListDto
import com.android.weather.forecast.data.model.response.MainDto
import com.android.weather.forecast.data.model.response.WeatherDto
import com.android.weather.forecast.data.model.response.WeatherForecastResponse
import com.android.weather.forecast.domain.model.Weather
import com.android.weather.forecast.domain.model.WeatherInfo
import com.android.weather.forecast.domain.model.WeatherItem
import java.text.SimpleDateFormat
import java.util.Locale

fun WeatherForecastResponse.toEntity(): List<WeatherItem> {
    val cityName = city.name
    val lat = city.coord.lat
    val lon = city.coord.lon
    return list.map { it.toEntity(cityName, lat, lon) }
}

fun ListDto.toEntity(cityName: String, lat: Double, lon: Double): WeatherItem {
    return WeatherItem(
        weatherInfo = main.toEntity(),
        weather = weather.map { it.toEntity() },
        cloud = clouds.all,
        windSpeed = wind.speed,
        dateText = formatDate(dateText),
        cityName = cityName,
        lat = lat,
        lon = lon
    )
}

fun MainDto.toEntity(): WeatherInfo {
    return WeatherInfo(
        temperature = temp,
        pressure = pressure,
        humidity = humidity,
    )
}

fun WeatherDto.toEntity(): Weather {
    return Weather(
        id = id,
        description = description,
        icon = icon
    )
}

fun formatDate(dateString: String): String {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date = format.parse(dateString)
    val dayFormat = SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.getDefault())
    return dayFormat.format(date)
}