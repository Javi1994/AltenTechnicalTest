package com.javi.data

import com.javi.data.datasource.UserDataSourceImpl
import com.javi.data.datasource.network.UserApi
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.javi.common.Resource
import com.javi.data.dto.UserDto
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking

class UserDataSourceImplTest {
    private lateinit var userDataSourceImpl: UserDataSourceImpl
    private lateinit var fakeUserApi: UserApi

    @Before
    fun setUp() {
        fakeUserApi = FakeUserApi()
        userDataSourceImpl = UserDataSourceImpl(fakeUserApi)
    }

    @Test
    fun `success get user, returns valid user`() {
        runBlocking {
            val user = userDataSourceImpl.getUser()
            assertThat(user).isNotNull()
            assertThat(user).isInstanceOf(UserDto::class.java)
        }
    }

    @Test
    fun `success get users, returns valid user list`() {
        val userCount = 5
        val firstTime = true

        runBlocking {
            val lastEmit = userDataSourceImpl.getUsers(userCount, firstTime).last()
            assertThat(lastEmit).isInstanceOf(Resource.Success::class.java)
            assertThat(lastEmit.data).isNotNull()
            assertThat(lastEmit.data?.first()).isInstanceOf(UserDto::class.java)
            assertThat(lastEmit.data?.size).isEqualTo(userCount)
        }
    }
}