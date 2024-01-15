package com.javi.data.datasource

import com.javi.common.Resource
import com.javi.data.datasource.network.UserApi
import com.javi.data.dto.UserDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class UserDataSourceImpl(
    private val userApi: UserApi
) : UserDataSource {
    override suspend fun getUser(): Flow<Resource<UserDto>> {
        return flow {
            emit(Resource.Loading(true))
            //TODO: Remove after testing
            delay(5_000)
            try {
                val user = userApi.getUser()
                emit(Resource.Success(user))
            } catch (e: IOException) {
                emit(Resource.Error(e))
            }
        }
    }
}