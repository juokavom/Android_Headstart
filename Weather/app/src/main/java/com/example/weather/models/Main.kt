package com.example.weather.models

import java.io.Serializable

data class Main(
    val temp: Double,
    val pressure: Double,
    val humidity: Int,
    val tempMin: Double,
    val tempMax: Double
) : Serializable