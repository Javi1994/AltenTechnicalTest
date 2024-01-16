package com.javi.presentation.contact_detail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javi.common.Resource
import com.javi.domain.use_case.GetUserByIdUseCase
import com.javi.domain.use_case.GetUsersUseCase
import com.javi.presentation.contact_list.viewmodel.ContactListNavigationEvent
import com.javi.presentation.contact_list.viewmodel.ContactListUiState
import com.javi.presentation.navigation.USER_ID_PARAM
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ContactDetailViewModel constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val navigationChannel = Channel<ContactDetailNavigationEvent>()
    val navigationEventsChannelFlow = navigationChannel.receiveAsFlow()

    var state by mutableStateOf(ContactDetailUiState())

    init {
        val userId = savedStateHandle.get<String>(USER_ID_PARAM).orEmpty()
        state = state.copy(userId = userId)
    }
}