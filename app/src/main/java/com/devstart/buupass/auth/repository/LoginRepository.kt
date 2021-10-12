package com.devstart.buupass.auth.repository

import com.devstart.buupass.data.api.ApiService
import com.devstart.buupass.data.model.Failure
import com.devstart.buupass.data.model.Success
import com.devstart.buupass.data.model.UserRequest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiService: ApiService) {

    fun loginUser(user: UserRequest) = flow {
        try {
            val data = apiService.fetchUser()
            emit(Success(data))
        }catch (t: Throwable) {
            emit(Failure(t))
        }

    }
}