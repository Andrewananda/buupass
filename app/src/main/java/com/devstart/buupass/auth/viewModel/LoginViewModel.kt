package com.devstart.buupass.auth.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devstart.buupass.auth.repository.LoginRepository
import com.devstart.buupass.data.model.ApiResponse
import com.devstart.buupass.data.model.UserRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {
    private val mutableUserResponse = MutableLiveData<ApiResponse>()
    fun getUserResponse() : LiveData<ApiResponse> = mutableUserResponse

    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())


    fun login(username: String, password: String){
        val userRequest = UserRequest(username, password)
        coroutineScope.launch {
            repository.loginUser(userRequest).collect {
                mutableUserResponse.postValue(it)
            }
        }
    }
}