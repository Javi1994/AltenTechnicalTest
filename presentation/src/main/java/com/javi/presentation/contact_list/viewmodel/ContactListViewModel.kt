package com.javi.presentation.contact_list.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.domain.use_case.GetUserUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ContactListViewModel constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val navigationChannel = Channel<ContactListNavigationEvent>()
    val navigationEventsChannelFlow = navigationChannel.receiveAsFlow()

    var state by mutableStateOf(ContactListUiState())

    init {
        viewModelScope.launch {
            getUserUseCase().collect { user ->
                state = state.copy(
                    userList = listOf(user, user, user),
                    isLoading = false,
                    hasError = false
                )
            }
        }
    }
}