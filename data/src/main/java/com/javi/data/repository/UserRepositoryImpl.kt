package com.javi.data.repository

import com.javi.common.Resource
import com.javi.data.datasource.UserDataSource
import com.javi.data.datasource.network.UserApi
import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun getUser(): Flow<Resource<UserDto>> {
        return userDataSource.getUser()
    }
}