package com.javi.data.di

import com.javi.data.datasource.UserDataSource
import com.javi.data.datasource.network.UserDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    includes(networkModule)
    includes(dispatcherModule)

    single<UserDataSource> {
        UserDataSourceImpl(get())
    }
}