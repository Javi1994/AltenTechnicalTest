package com.javi.data.repository

import com.javi.common.Resource
import com.javi.data.datasource.UserDataSource
import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun getUsers(count: Int): Flow<Resource<List<UserDto>>> {
        return userDataSource.getUsers(count)
    }

    override suspend fun getUserById(userId: String): Flow<Resource<UserDto>> {
        return userDataSource.getUserById(userId)
    }
}