package com.example.demoweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demoweather.adapter.CityListAdapter
import com.example.demoweather.databinding.SelectCityFragmentBinding
import com.example.demoweather.model.City
import com.google.android.material.transition.MaterialFadeThrough

class SelectCityFragment : Fragment() {

    private lateinit var adapter: CityListAdapter
    private lateinit var binding: SelectCityFragmentBinding
    private val cities = mutableListOf(City("Delhi"), City("Mumbai"), City("Kolkata"), City("Bengaluru"), City("London"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SelectCityFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CityListAdapter()
        binding.rvCities.adapter = adapter

        adapter.submitList(cities)
    }

    companion object {
        fun newInstance() = SelectCityFragment()
        val TAG = SelectCityFragment::class.java.simpleName
    }
}