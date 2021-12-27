package com.example.demoweather.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.demoweather.model.City
import com.example.demoweather.model.Weather

class CityDiffCallback : DiffUtil.ItemCallback<City>(){
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }
}

@BindingAdapter("city")
fun TextView.setCity(city: City?) {
    city?.let {
        text = it.name
    }
}