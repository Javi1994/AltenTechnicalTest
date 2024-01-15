package com.javi.data.di

import com.javi.data.repository.UserRepository
import com.javi.data.repository.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    includes(dataSourceModule)

    single<UserRepository> {
        UserRepositoryImpl(get())
    }
}