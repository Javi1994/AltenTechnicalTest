package com.javi.data

import android.net.http.HttpException
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
import java.io.IOException

class UserDataSourceImplTest {
    private lateinit var userDataSourceImpl: UserDataSourceImpl
    private lateinit var fakeUserApi: FakeUserApi

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
        val userCount = 10
        val firstTime = true

        runBlocking {
            val lastEmit = userDataSourceImpl.getUsers(userCount, firstTime).last()
            assertThat(lastEmit).isInstanceOf(Resource.Success::class.java)
            assertThat(lastEmit.data).isNotNull()
            assertThat(lastEmit.data?.first()).isInstanceOf(UserDto::class.java)
            assertThat(lastEmit.data?.size).isEqualTo(userCount)
        }
    }

    @Test
    fun `success get users first loading, returns loading resource`() {
        val userCount = 5
        val firstTime = true

        runBlocking {
            val firstEmit = userDataSourceImpl.getUsers(userCount, firstTime).first()
            assertThat(firstEmit).isInstanceOf(Resource.Loading::class.java)
            assertThat(firstEmit.isLoading).isTrue()
        }
    }

    @Test
    fun `success get user by id, returns valid user`() {
        runBlocking {
            val user = userDataSourceImpl.getUser()
            val userId = user.login.uuid

            userDataSourceImpl.getUserById(userId).collect {
                assertThat(it).isInstanceOf(Resource.Success::class.java)
                assertThat(it.data).isNotNull()
                assertThat(it.data).isInstanceOf(UserDto::class.java)
                assertThat(it.data?.login?.uuid).isEqualTo(userId)
            }
        }
    }

    @Test
    fun `error get user by id, returns nullpointer error`() {
        runBlocking {
            val userId = "no id"

            userDataSourceImpl.getUserById(userId).collect {
                assertThat(it).isInstanceOf(Resource.Error::class.java)
                assertThat(it.error).isNotNull()
                assertThat(it.error).isInstanceOf(NullPointerException::class.java)
            }
        }
    }

    @Test
    fun `error get users ioexception, returns ioexception error resource`() {
        val userCount = 5
        val firstTime = true
        fakeUserApi.shouldThrowError(IOException())

        runBlocking {
            val lastEmit = userDataSourceImpl.getUsers(userCount, firstTime).last()
            assertThat(lastEmit).isInstanceOf(Resource.Error::class.java)
            assertThat(lastEmit.error).isNotNull()
            assertThat(lastEmit.error).isInstanceOf(IOException::class.java)
        }
    }

    @Test
    fun `error get users httpexception, returns httpexception error resource`() {
        val userCount = 5
        val firstTime = true
        fakeUserApi.shouldThrowError(HttpException("", NullPointerException()))

        runBlocking {
            val lastEmit = userDataSourceImpl.getUsers(userCount, firstTime).last()
            assertThat(lastEmit).isInstanceOf(Resource.Error::class.java)
            assertThat(lastEmit.error).isNotNull()
            assertThat(lastEmit.error).isInstanceOf(HttpException::class.java)
        }
    }

    @Test
    fun `error get users nullpointer, returns nullpointer error resource`() {
        val userCount = 5
        val firstTime = true
        fakeUserApi.shouldThrowError(NullPointerException())

        runBlocking {
            val lastEmit = userDataSourceImpl.getUsers(userCount, firstTime).last()
            assertThat(lastEmit).isInstanceOf(Resource.Error::class.java)
            assertThat(lastEmit.error).isNotNull()
            assertThat(lastEmit.error).isInstanceOf(NullPointerException::class.java)
        }
    }
}