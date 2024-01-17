package com.javi.data

import com.javi.common.Resource
import com.javi.data.dto.UserDto
import com.javi.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class FakeUserRepository : UserRepository {

    private var userList = mutableListOf<UserDto>()
    private var userCount = 0

    private var shouldReturnError = false
    private var error: Exception? = null

    private var shouldReturnLoading = false

    override suspend fun getUsers(count: Int, firstTime: Boolean): Flow<Resource<List<UserDto>>> {
        return flow {
            if (shouldReturnError) {
                emit(Resource.Error(error))
            } else if (shouldReturnLoading) {
                emit(Resource.Loading())
            } else {
                emit(Resource.Success(userList))
            }
        }
    }

    override suspend fun getUserById(userId: String): Flow<Resource<UserDto>> {
        TODO("Not yet implemented")
    }

    fun setUsersData(user: UserDto, userCount: Int) {
        userList = mutableListOf()
        this.userCount = userCount
        this.shouldReturnError = false
        this.shouldReturnLoading = false

        for (i in 1..userCount) {
            userList.add(user)
        }
    }

    fun shouldReturnError(error: Exception? = IOException("Undefined Error")) {
        this.shouldReturnError = true
        this.error = error
    }

    fun shouldReturnLoading() {
        this.shouldReturnLoading = true
        this.shouldReturnError = false
    }
}