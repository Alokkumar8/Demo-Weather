package com.example.demoweather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.demoweather.adapter.CityListAdapter
import com.example.demoweather.databinding.SelectCityFragmentBinding
import com.example.demoweather.databinding.WeatherFragmentBinding
import com.google.android.material.transition.MaterialFadeThrough
import org.json.JSONObject

class WeatherFragment : Fragment() {

    private val viewModel: WeatherFragmentViewModel by lazy {
        ViewModelProvider(this).get(WeatherFragmentViewModel::class.java)
    }
    private lateinit var binding: WeatherFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = WeatherFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val city = requireArguments().getString("city")

        viewModel.getWeather(city!!)
        viewModel.weather.observe(viewLifecycleOwner, {
            if (it != null && it.isNotEmpty()) {
                val json = JSONObject(it)
                val time = json.getJSONObject("current").getString("last_updated")
                val temperature = json.getJSONObject("current").getString("temp_c")
                val condition = json.getJSONObject("current").getJSONObject("condition").getString("text")
                val wind = json.getJSONObject("current").getString("wind_kph")
                val humidity = json.getJSONObject("current").getString("humidity")
                val cloud = json.getJSONObject("current").getString("cloud")

                binding.tvCity.text = city
                binding.tvTime.text = time
                binding.tvTemperature.text = "${temperature}°"
                binding.tvCondition.text = condition
                binding.tvWind.text = wind
                binding.tvHumidity.text = humidity
                binding.tvCloud.text = cloud

                when {
                    temperature.toInt() in 0..25 -> {
                        binding.ivWeather.background = resources.getDrawable(R.drawable.cold)
                    }
                    temperature.toInt() in 25..59 -> {
                        binding.ivWeather.background = resources.getDrawable(R.drawable.cloudy)
                    }
                }
            }
        })
    }

    companion object {
        fun newInstance() = WeatherFragment()
        val TAG = WeatherFragment::class.java.simpleName
    }

}