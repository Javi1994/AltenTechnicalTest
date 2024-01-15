package com.javi.data.di

import com.javi.data.datasource.mock.UserMockApi
import com.javi.data.datasource.network.UserApi
import com.javi.data.repository.UserRepository
import com.javi.data.repository.UserRepositoryImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mockModule = module {
    single<UserApi> {
        UserMockApi()
    }
}