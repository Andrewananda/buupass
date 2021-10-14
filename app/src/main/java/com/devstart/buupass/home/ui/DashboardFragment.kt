package com.devstart.buupass.home.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.devstart.buupass.R
import com.devstart.buupass.data.model.PrefUser
import com.devstart.buupass.databinding.FragmentDashboardBinding
import com.devstart.buupass.home.model.CarModel
import com.devstart.buupass.prefs
import com.google.gson.Gson
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
        binding.moreView.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_carsFragment)
        }
        binding.profileImg.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_profileFragment)
        }

        binding.cardView.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_carsFragment)
        }

        binding.btnRetry.setOnClickListener {
            displayViewContent()
        }

        displayViewContent()
    }

    private fun displayViewContent() {
        if (checkConnectivity()){
            binding.mainView.visibility = View.VISIBLE
            binding.internetConnection.visibility = View.GONE
            loadView()
        }else {
            binding.mainView.visibility = View.GONE
            binding.internetConnection.visibility = View.VISIBLE
        }
    }

    private fun checkConnectivity(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    private fun loadView() {
        val user = Gson().fromJson(prefs.userPref, PrefUser::class.java)
        Glide.with(this).load(user.imageUrl)
            .placeholder(R.drawable.logo)
            .circleCrop()
            .into(binding.profileImg)
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