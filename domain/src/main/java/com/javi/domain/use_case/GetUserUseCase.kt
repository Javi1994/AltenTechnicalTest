package com.javi.domain.use_case

import com.javi.data.repository.UserRepository
import com.javi.domain.mapping.toUser
import com.javi.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class GetUserUseCase constructor(
    private val userRepository: UserRepository,
    private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Flow<User> = withContext(defaultDispatcher) {
        return@withContext userRepository.getUser().map {
            it.toUser()
        }
    }
}