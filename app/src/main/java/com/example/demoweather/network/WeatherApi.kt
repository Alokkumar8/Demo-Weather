package com.example.demoweather.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://api.weatherapi.com/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    @GET("current.json")
    fun getWeather(@Query("q") city: String, @Query("aqi") type: String="no", @Query("key") key: String="f4e9d7d66ec44749bbe122715212612"): Call<String>
}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}