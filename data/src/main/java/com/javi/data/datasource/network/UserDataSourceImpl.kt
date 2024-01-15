package com.javi.data.datasource.network

import com.javi.data.datasource.UserDataSource
import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserDataSourceImpl(
    private val userApi: UserApi
) : UserDataSource {
    override fun getUser(): Flow<UserDto> {
        return flow {
            userApi.getUser()
        }
    }
}