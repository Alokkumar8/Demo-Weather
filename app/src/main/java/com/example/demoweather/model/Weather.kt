package com.example.demoweather.model

data class Weather(
    var id: String = "",
    var temperature: String = "",
    var body: String = "",
    var timestamp: Long = 0L,
    var language: String = "",
    var user_id: String = ""
)
