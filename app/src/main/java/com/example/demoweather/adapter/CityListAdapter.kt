package com.example.demoweather.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demoweather.MainActivity
import com.example.demoweather.R
import com.example.demoweather.SelectCityFragment
import com.example.demoweather.WeatherFragment
import com.example.demoweather.databinding.CityRowBinding
import com.example.demoweather.model.City
import com.example.demoweather.utils.CityDiffCallback

class CityListAdapter : ListAdapter<City, CityListAdapter.ViewHolder>(CityDiffCallback()) {

    class ViewHolder private constructor(private val binding: CityRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(city: City) {

            binding.city = city

            binding.root.setOnClickListener {

                val fragment = WeatherFragment()
                val bundle = Bundle()
                bundle.putString("city", city.name)
                fragment.arguments = bundle

                val fm = (binding.root.context as MainActivity).supportFragmentManager
                fm.beginTransaction()
                    .add(R.id.frame_container, fragment, WeatherFragment.TAG)
                    .hide(fm.findFragmentByTag(SelectCityFragment.TAG)!!)
                    .addToBackStack(null)
                    .commit()
            }
        }

        companion object {

            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CityRowBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}