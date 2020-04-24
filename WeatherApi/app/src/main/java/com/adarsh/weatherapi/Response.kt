package com.adarsh.weatherapi

data class Response(
	val weather: List<WeatherItem?>? = null,
	val main: Main? = null,
	val base: String? = null
)

data class Main(
	val temp: Int? = null,
	val tempMin: Int? = null,
	val humidity: Int? = null,
	val pressure: Int? = null,
	val feelsLike: Double? = null,
	val tempMax: Int? = null
)

data class WeatherItem(
	val icon: String? = null,
	val description: String? = null,
	val main: String? = null,
	val id: Int? = null
)

