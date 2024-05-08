package com.android.weather.forecast.data.model.response

import com.google.gson.annotations.SerializedName

data class WeatherForecastResponse(
    @SerializedName("city")
    var city: CityDto,
    @SerializedName("cod")
    var cod: String,
    @SerializedName("message")
    var message: Double,
    @SerializedName("cnt")
    var cnt: Int,
    @SerializedName("list")
    var list: List<ListDto>
)

data class CityDto(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("coord")
    var coord: CoordDto,
    @SerializedName("country")
    var country: String,
    @SerializedName("population")
    var population: Double,
    @SerializedName("timezone")
    var timezone: Int,
    @SerializedName("sunrise")
    var sunrise: Long,
    @SerializedName("sunset")
    var sunset: Long,
)

data class CoordDto(
    @SerializedName("lon")
    var lon: Double,
    @SerializedName("lat")
    var lat: Double
)

data class ListDto(
    @SerializedName("dt")
    val date: Long,
    @SerializedName("main")
    val main: MainDto,
    @SerializedName("weather")
    val weather: List<WeatherDto>,
    @SerializedName("clouds")
    val clouds: CloudsDto,
    @SerializedName("wind")
    val wind: WindDto,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("sys")
    val system: SystemDto,
    @SerializedName("dt_txt")
    val dateText: String,
)

data class MainDto(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("sea_level")
    val seaLevel: Int,
    @SerializedName("grnd_level")
    val grndLevel: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("temp_kf")
    val tempKf: Double,
)

data class WeatherDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
)

data class CloudsDto(
    @SerializedName("all")
    val all: Int,
)

data class WindDto(
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val degree: Int,
    @SerializedName("gust")
    val gust: Double,
)

data class SystemDto(
    @SerializedName("pod")
    val pod: String,
)