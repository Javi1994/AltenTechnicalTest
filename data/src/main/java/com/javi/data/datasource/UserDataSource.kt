package com.javi.data.datasource

import com.javi.common.Resource
import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    suspend fun getUsers(count: Int, firstTime: Boolean): Flow<Resource<List<UserDto>>>

    suspend fun getUserById(userId: String): Flow<Resource<UserDto>>
}