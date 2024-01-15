package com.javi.domain.di

import com.javi.data.di.repositoryModule
import org.koin.core.module.dsl.factoryOf
import com.javi.domain.use_case.GetUserUseCase
import org.koin.dsl.module

val useCaseModule = module {
    includes(repositoryModule)
    includes(dispatcherModule)

    factoryOf(::GetUserUseCase)
}