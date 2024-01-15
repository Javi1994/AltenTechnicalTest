package com.javi.domain.use_case

import com.javi.common.Resource
import com.javi.data.repository.UserRepository
import com.javi.domain.mapping.toUser
import com.javi.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class GetUsersUseCase constructor(
    private val userRepository: UserRepository,
    private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(count: Int = 20): Flow<Resource<List<User>>> = withContext(defaultDispatcher) {
        return@withContext userRepository.getUsers(count).map {
            when (it) {
                is Resource.Success -> {
                    Resource.Success(data = it.data?.map { userDto ->
                        userDto.toUser()
                    })
                }

                is Resource.Loading -> {
                    Resource.Loading(isLoading = it.isLoading)
                }

                is Resource.Error -> {
                    Resource.Error(error = it.error)
                }
            }
        }
    }
}