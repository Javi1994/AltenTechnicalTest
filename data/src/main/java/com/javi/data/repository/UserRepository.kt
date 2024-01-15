package com.javi.data.repository

import com.javi.common.Resource
import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUsers(count: Int = 10): Flow<Resource<List<UserDto>>>
}