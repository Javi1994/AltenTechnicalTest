package com.javi.data.datasource

import com.javi.common.Resource
import com.javi.data.datasource.network.UserApi
import com.javi.data.dto.UserDto
import com.javi.data.dto.UserResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserDataSourceImpl(
    private val userApi: UserApi
) : UserDataSource {
    suspend fun getUser(): UserDto {
        return userApi.getUser().results.first()
    }

    override suspend fun getUsers(count: Int): Flow<Resource<List<UserDto>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            val userList: MutableList<UserDto> = mutableListOf()
            for (i in 0..count) {
                val user = getUser()
                userList.add(user)
            }

            emit(Resource.Success(userList))
        }
    }
}