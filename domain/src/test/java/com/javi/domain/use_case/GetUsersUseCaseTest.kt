package com.javi.domain.use_case

import com.javi.common.Resource
import com.javi.data.FakeUserData
import com.javi.data.FakeUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import com.google.common.truth.Truth.assertThat
import com.javi.domain.model.User
import org.junit.Test
import java.io.IOException


class GetUsersUseCaseTest {

    private lateinit var getUsersUseCase: GetUsersUseCase
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp() {
        fakeUserRepository = FakeUserRepository()
        getUsersUseCase = GetUsersUseCase(fakeUserRepository, Dispatchers.Unconfined)
    }

    @Test
    fun `success response, returns valid success resource`() {
        val userCount = 5
        fakeUserRepository.setUsersData(FakeUserData.validUserDto, userCount)

        runBlocking {
            getUsersUseCase(userCount, true).collect {
                assertThat(it).isInstanceOf(Resource.Success::class.java)
                assertThat(it.data).isNotNull()
                assertThat(it.data?.first()).isInstanceOf(User::class.java)
            }
        }
    }

    @Test
    fun `success response with 5 users request, returns valid success resource with 5 users`() {
        val userCount = 5
        fakeUserRepository.setUsersData(FakeUserData.validUserDto, userCount)

        runBlocking {
            getUsersUseCase(userCount, true).collect {
                assertThat(it).isInstanceOf(Resource.Success::class.java)
                assertThat(it.data).isNotNull()
                assertThat(it.data?.first()).isInstanceOf(User::class.java)
                assertThat(it.data?.size).isEqualTo(5)
            }
        }
    }

    @Test
    fun `loading response, returns valid loading resource`() {
        val userCount = 5
        fakeUserRepository.shouldReturnLoading()

        runBlocking {
            getUsersUseCase(userCount, true).collect {
                assertThat(it).isInstanceOf(Resource.Loading::class.java)
                assertThat(it.isLoading).isTrue()
            }
        }
    }

    @Test
    fun `error response, returns valid error resource`() {
        val userCount = 5
        fakeUserRepository.shouldReturnError()

        runBlocking {
            getUsersUseCase(userCount, true).collect {
                assertThat(it).isInstanceOf(Resource.Error::class.java)
                assertThat(it.error).isNotNull()
                assertThat(it.error).isInstanceOf(IOException::class.java)
            }
        }
    }
}