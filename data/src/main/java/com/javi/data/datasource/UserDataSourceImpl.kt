package com.javi.data.datasource

import com.javi.common.Resource
import com.javi.data.datasource.network.UserApi
import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class UserDataSourceImpl(
    private val userApi: UserApi
) : UserDataSource {

    private val userList: MutableList<UserDto> = mutableListOf()

    suspend fun getUser(): UserDto {
        val user = userApi.getUser().results.first()
        userList.add(user)
        return user
    }

    override suspend fun getUsers(count: Int): Flow<Resource<List<UserDto>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))

            try {
                val userList: MutableList<UserDto> = mutableListOf()
                for (i in 0..count) {
                    val user = getUser()
                    userList.add(user)
                }

                emit(Resource.Success(userList))
            } catch (e: IOException) {
                emit(Resource.Error(e))
            } catch (e: HttpException) {
                emit(Resource.Error(e))
            } catch (e: NullPointerException) {
                emit(Resource.Error(e))
            }
        }
    }

    override suspend fun getUserById(userId: String): Flow<Resource<UserDto>> {
        return flow {
            val user = userList.find {
                it.login.uuid == userId
            }

            if (user != null) {
                emit(Resource.Success(user))
            } else {
                emit(Resource.Error(NullPointerException("User not found")))
            }
        }
    }
}