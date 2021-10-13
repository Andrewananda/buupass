package com.devstart.buupass.cars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.devstart.buupass.R
import com.devstart.buupass.databinding.FragmentCarsBinding
import com.devstart.buupass.home.model.CarModel
import com.devstart.buupass.home.ui.CarAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarsFragment : Fragment() {
    private lateinit var binding: FragmentCarsBinding
    private lateinit var adapter: CarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCarsBinding.inflate(layoutInflater, container, false)
        adapter = CarAdapter()
        binding.recyclerview.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cars = loadCars()
        adapter.submitList(cars)
    }

    private fun loadCars(): MutableList<CarModel> {
        return mutableListOf(
            CarModel("https://cdn.pixabay.com/photo/2015/09/02/12/25/bmw-918408_1280.jpg","BMW","Kes 10,000","Per Week","BMW"),
            CarModel("https://cdn.pixabay.com/photo/2017/03/27/14/56/auto-2179220_1280.jpg","Mercedes","Kes 30,000","Per Day","Mercedes Benz"),
            CarModel("https://cdn.pixabay.com/photo/2015/05/15/14/46/bmw-768688_1280.jpg","BMW","Kes 10,000","Per Hour","Bmw"),
            CarModel("https://cdn.pixabay.com/photo/2015/12/08/00/28/car-1081742_1280.jpg","Lamborghini","Kes 50,000","Per Day","Lamborghini"),
            CarModel("https://cdn.pixabay.com/photo/2015/01/19/13/51/car-604019_1280.jpg","Audi","Kes 5,000","Per Dy","Audi 4"),
        )

    }
}