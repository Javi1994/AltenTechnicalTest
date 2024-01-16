package com.javi.presentation.di

import com.javi.domain.di.useCaseModule
import org.koin.androidx.viewmodel.dsl.viewModelOf
import com.javi.presentation.features.contact_list.viewmodel.ContactListViewModel
import com.javi.presentation.features.contact_detail.viewmodel.ContactDetailViewModel
import org.koin.dsl.module

val contactListModule = module {
    includes(useCaseModule)

    viewModelOf(::ContactListViewModel)
    viewModelOf(::ContactDetailViewModel)
}