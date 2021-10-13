package com.devstart.buupass.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.devstart.buupass.R
import com.devstart.buupass.data.model.PrefUser
import com.devstart.buupass.databinding.FragmentProfileBinding
import com.devstart.buupass.prefs
import com.devstart.buupass.profile.model.HireModel
import com.google.gson.Gson
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
        setUpUserData()
    }

    private fun setUpUserData() {
        val user = Gson().fromJson(prefs.userPref, PrefUser::class.java)
        binding.username.text = user.username
        binding.email.text = user.email

        Glide.with(this).load(user.imageUrl)
            .placeholder(R.drawable.logo_no_bg)
            .into(binding.profileImg)
    }

    private fun loadHireItems() : MutableList<HireModel> {
        return mutableListOf(
            HireModel("https://cdn.pixabay.com/photo/2015/09/02/12/25/bmw-918408_1280.jpg","1500h 13-10-2021","enquiry"),
            HireModel("https://cdn.pixabay.com/photo/2015/12/08/00/28/car-1081742_1280.jpg","1300h 07-10-2021","onTransit"),
            HireModel("https://cdn.pixabay.com/photo/2015/01/19/13/51/car-604019_1280.jpg","Sept 13th 2021","returned"),
        )
    }
}