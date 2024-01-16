package com.javi.presentation.contact_detail.viewmodel

import com.javi.domain.model.User
import com.javi.presentation.contact_list.viewmodel.ContactListNavigationEvent


data class ContactDetailUiState(
    val userId: String? = null,
    val user: User? = null,
    val isInEditMode: Boolean = false,
    val isLoading: Boolean = false,
    val error: Exception? = null
) {

}

sealed class ContactDetailUiEvent {

}

sealed class ContactDetailNavigationEvent {

}