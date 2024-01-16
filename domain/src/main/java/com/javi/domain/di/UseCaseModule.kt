package com.javi.domain.di

import com.javi.data.di.repositoryModule
import org.koin.core.module.dsl.factoryOf
import com.javi.domain.use_case.GetUsersUseCase
import com.javi.domain.use_case.GetUserByIdUseCase
import org.koin.dsl.module

val useCaseModule = module {
    includes(repositoryModule)
    includes(dispatcherModule)

    factoryOf(::GetUsersUseCase)
    factoryOf(::GetUserByIdUseCase)
}