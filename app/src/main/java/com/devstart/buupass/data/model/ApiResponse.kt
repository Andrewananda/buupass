package com.devstart.buupass.data.model

sealed class ApiResponse

data class Success<T>(val data: T): ApiResponse()

class Failure(val throwable: Throwable) : ApiResponse()