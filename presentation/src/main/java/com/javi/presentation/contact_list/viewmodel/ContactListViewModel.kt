package com.javi.presentation.contact_list.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.common.Resource
import com.javi.domain.model.User
import com.javi.domain.use_case.GetUsersUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ContactListViewModel constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val navigationChannel = Channel<ContactListNavigationEvent>()
    val navigationEventsChannelFlow = navigationChannel.receiveAsFlow()

    var state by mutableStateOf(ContactListUiState())

    init {
        onEvent(ContactListUiEvent.OnLoadFirstUsers)
    }

    fun onEvent(event: ContactListUiEvent) {
        when (event) {
            ContactListUiEvent.OnLoadFirstUsers -> {
                getUserList(10, true)
            }

            ContactListUiEvent.OnLoadMoreUsers -> {
                getUserList(10)
            }

            is ContactListUiEvent.OnUserClick -> {
                viewModelScope.launch {
                    navigationChannel.send(ContactListNavigationEvent.NavigateToUserDetail(event.userId))
                }
            }

            is ContactListUiEvent.OnSearchUser -> {
                val filteredUsers = filterUsers(event.search)
                state = state.copy(
                    searchUser = event.search,
                    filteredUserList = filteredUsers,
                    isLoading = false
                )
            }
        }
    }

    private fun getUserList(count: Int, resetUsers: Boolean = false) {
        viewModelScope.launch {
            getUsersUseCase(count).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { userList ->
                            state = if (resetUsers) {
                                state.copy(
                                    userList = userList,
                                    isLoading = false
                                )
                            } else {
                                state.copy(
                                    userList = state.userList + userList,
                                    isLoading = false
                                )
                            }
                        }
                    }

                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading && resetUsers)
                    }

                    is Resource.Error -> {
                        state = state.copy(error = result.error, isLoading = false)
                    }
                }
            }
        }
    }

    private fun filterUsers(text: String): List<User> {
        val users = state.userList
        return users.filter { user ->
            user.name.toLowerCase(Locale.current)
                .contains(text.toLowerCase(Locale.current))
                    || user.email.toLowerCase(Locale.current)
                .contains(text.toLowerCase(Locale.current))
        }
    }
}