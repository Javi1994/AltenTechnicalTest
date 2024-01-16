package com.javi.presentation.features.contact_list.viewmodel

import com.javi.domain.model.User


data class ContactListUiState(
    val userList: List<User> = listOf(),
    val filteredUserList: List<User> = listOf(),
    val searchUser: String = "",
    val isLoading: Boolean = false,
    val error: Exception? = null
) {
    val hasUsers: Boolean
        get() = userList.isNotEmpty()
    val hasError: Boolean
        get() = error != null
    val isSearching: Boolean
        get() = searchUser.isNotEmpty()
}

sealed class ContactListUiEvent {
    data class OnUserClick(val userId: String) : ContactListUiEvent()
    data object OnLoadFirstUsers : ContactListUiEvent()
    data object OnLoadMoreUsers : ContactListUiEvent()
    data class OnSearchUser(val search: String) : ContactListUiEvent()
}

sealed class ContactListNavigationEvent {
    data class NavigateToUserDetail(val userId: String) : ContactListNavigationEvent()
}