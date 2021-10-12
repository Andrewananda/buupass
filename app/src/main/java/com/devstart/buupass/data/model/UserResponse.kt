package com.devstart.buupass.data.model

data class UserResponse(
    val data: User,
    val support: SupportResponse
)

data class SupportResponse(
    val url: String?,
    val text: String?
)