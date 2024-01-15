package com.javi.data.datasource.network

import com.javi.data.dto.UserDto
import com.javi.data.dto.UserResponse
import retrofit2.http.GET

interface UserApi {
    @GET("api/")
    suspend fun getUser(): UserResponse
}