package com.devstart.buupass.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devstart.buupass.R
import com.devstart.buupass.databinding.FragmentProfileBinding
import com.devstart.buupass.profile.model.HireModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var adapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        adapter = context?.let { ProfileAdapter(it) }!!
        binding.recyclerview.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hireItems = loadHireItems()
        adapter.submitList(hireItems)
    }

    private fun loadHireItems() : MutableList<HireModel> {
        return mutableListOf(
            HireModel("https://cdn.pixabay.com/photo/2015/09/02/12/25/bmw-918408_1280.jpg","1500h 13-10-2021","enquiry"),
            HireModel("https://cdn.pixabay.com/photo/2015/12/08/00/28/car-1081742_1280.jpg","1300h 07-10-2021","onTransit"),
            HireModel("https://cdn.pixabay.com/photo/2015/01/19/13/51/car-604019_1280.jpg","Sept 13th 2021","returned"),
        )
    }
}