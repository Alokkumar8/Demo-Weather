package com.example.demoweather

import retrofit2.Call
import retrofit2.await
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoweather.network.WeatherApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class WeatherApiStatus { LOADING, SUCCESS, ERROR }
class WeatherFragmentViewModel : ViewModel() {

    private var weatherDeferred: Call<String>? = null

    private val _weather = MutableLiveData<String>()
    val weather: LiveData<String>
        get() = _weather

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getWeather(city: String) {

        coroutineScope.launch {

            weatherDeferred = WeatherApi.retrofitService.getWeather(city)
            try {
                val result = weatherDeferred!!.await()
                _weather.value = result
            } catch (t: Throwable) {
                _weather.value = ""
            }
        }
    }
}