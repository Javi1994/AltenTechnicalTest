package com.javi.data.datasource.network

import com.javi.data.dto.UserDto

interface UserApi {
    suspend fun getUser(): UserDto
}