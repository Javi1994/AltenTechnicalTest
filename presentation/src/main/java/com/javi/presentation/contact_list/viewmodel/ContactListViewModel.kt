package com.javi.presentation.contact_list.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.common.Resource
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
        getUserList()
    }

    private fun getUserList() {
        viewModelScope.launch {
            getUsersUseCase(20).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { userList ->
                            state = state.copy(
                                userList = userList,
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Loading -> {
                        state = state.copy(isLoading = true)
                    }

                    is Resource.Error -> {
                        state = state.copy(error = result.error)
                    }
                }
            }
        }
    }
}