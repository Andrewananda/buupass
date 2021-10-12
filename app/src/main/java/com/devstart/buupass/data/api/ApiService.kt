package com.devstart.buupass.data.api

import com.devstart.buupass.data.model.UserResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users/2")
    fun fetchUser() : UserResponse
}