package com.devstart.buupass.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.devstart.buupass.databinding.FragmentDashboardBinding
import com.devstart.buupass.home.model.CarModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var adapter: CarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        adapter = CarAdapter()
        binding.recyclerview.adapter = adapter
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerview.layoutManager = layoutManager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadView()
    }

    private fun loadView() {
        val cars = loadCars()
        adapter.submitList(cars)
    }

    private fun loadCars(): MutableList<CarModel> {
        return mutableListOf<CarModel>(
            CarModel("https://cdn.pixabay.com/photo/2015/09/02/12/25/bmw-918408_1280.jpg","BMW","","",""),
            CarModel("https://cdn.pixabay.com/photo/2017/03/27/14/56/auto-2179220_1280.jpg","Mercedes","","",""),
            CarModel("https://cdn.pixabay.com/photo/2015/05/15/14/46/bmw-768688_1280.jpg","BMW","","",""),
            CarModel("https://cdn.pixabay.com/photo/2015/12/08/00/28/car-1081742_1280.jpg","lamborghini","","",""),
            CarModel("https://cdn.pixabay.com/photo/2015/01/19/13/51/car-604019_1280.jpg","Audi","","",""),
        )

    }


}