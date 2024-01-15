package com.javi.presentation.contact_list.viewmodel

import com.javi.domain.model.User


data class ContactListUiState(
    val userList: List<User> = listOf(),
    val isLoading: Boolean = false,
    val error: Exception? = null
)

sealed class ContactListUiEvent {
    data object OnUserClick : ContactListUiEvent()
}

sealed class ContactListNavigationEvent {
    data object NavigateToUserDetail : ContactListNavigationEvent()
}