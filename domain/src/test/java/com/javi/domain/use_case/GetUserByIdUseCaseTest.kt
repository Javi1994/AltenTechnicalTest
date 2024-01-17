package com.javi.domain.use_case

import com.google.common.truth.Truth
import com.javi.common.Resource
import com.javi.data.FakeUserData
import com.javi.data.FakeUserRepository
import com.javi.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import java.io.IOException

class GetUserByIdUseCaseTest {
    private lateinit var getUserByIdUseCase: GetUserByIdUseCase
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp() {
        fakeUserRepository = FakeUserRepository()
        getUserByIdUseCase = GetUserByIdUseCase(fakeUserRepository, Dispatchers.Unconfined)
    }

    @Test
    fun `success response, returns valid success resource`() {
        val userId = "7b4c522d-0738-4a74-83dd-efa318c2c570"
        fakeUserRepository.setUserIdData(FakeUserData.validUserDto)

        runBlocking {
            getUserByIdUseCase(userId).collect {
                assertThat(it).isInstanceOf(Resource.Success::class.java)
                assertThat(it.data).isNotNull()
                assertThat(it.data).isInstanceOf(User::class.java)
            }
        }
    }

    @Test
    fun `success response with id, returns valid success resource with same id`() {
        val userId = "7b4c522d-0738-4a74-83dd-efa318c2c570"
        fakeUserRepository.setUserIdData(FakeUserData.validUserDto)

        runBlocking {
            getUserByIdUseCase(userId).collect {
                assertThat(it).isInstanceOf(Resource.Success::class.java)
                assertThat(it.data).isNotNull()
                assertThat(it.data).isInstanceOf(User::class.java)
                assertThat(it.data?.id).isEqualTo(userId)
            }
        }
    }

    @Test
    fun `loading response, returns valid loading resource`() {
        val userId = "7b4c522d-0738-4a74-83dd-efa318c2c570"
        fakeUserRepository.shouldReturnLoading()

        runBlocking {
            getUserByIdUseCase(userId).collect {
                assertThat(it).isInstanceOf(Resource.Loading::class.java)
                assertThat(it.isLoading).isTrue()
            }
        }
    }

    @Test
    fun `error response, returns valid error resource`() {
        val userId = "7b4c522d-0738-4a74-83dd-efa318c2c570"
        fakeUserRepository.shouldReturnError()

        runBlocking {
            getUserByIdUseCase(userId).collect {
                assertThat(it).isInstanceOf(Resource.Error::class.java)
                assertThat(it.error).isNotNull()
                assertThat(it.error).isInstanceOf(IOException::class.java)
            }
        }
    }
}

