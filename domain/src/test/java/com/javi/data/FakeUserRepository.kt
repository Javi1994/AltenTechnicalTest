package com.javi.data

import com.javi.common.Resource
import com.javi.data.dto.UserDto
import com.javi.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class FakeUserRepository : UserRepository {

    private var userList = listOf<UserDto>()

    private var shouldReturnError = false
    private var error: Exception? = null

    private var shouldReturnLoading = false

    override suspend fun getUsers(count: Int, firstTime: Boolean): Flow<Resource<List<UserDto>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserById(userId: String): Flow<Resource<UserDto>> {
        TODO("Not yet implemented")
    }

}