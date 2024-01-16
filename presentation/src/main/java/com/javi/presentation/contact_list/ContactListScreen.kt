package com.javi.presentation.contact_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Button

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.javi.domain.model.User
import com.javi.presentation.components.ContactItem
import com.javi.presentation.components.CustomLoaderItem
import com.javi.presentation.components.EmptyDataItem
import com.javi.presentation.components.ErrorDataItem
import com.javi.presentation.contact_list.viewmodel.ContactListUiEvent
import com.javi.presentation.contact_list.viewmodel.ContactListUiState
import com.javi.presentation.contact_list.viewmodel.ContactListViewModel
import com.javi.presentation.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(
    navController: NavController,
    viewModel: ContactListViewModel = koinViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Contacts") })
        }
    ) { paddingValues ->
        ContactListLayout(
            state = viewModel.state,
            onUserClick = {
                navController.navigate("${Screen.ContactDetailScreen.route}/$it")
            },
            onLastItemReached = {
                viewModel.onEvent(ContactListUiEvent.OnLoadMoreUsers)
            },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun ContactListLayout(
    state: ContactListUiState,
    modifier: Modifier = Modifier,
    onLastItemReached: () -> Unit,
    onUserClick: (String) -> Unit
) {
    if (state.isLoading) {
        CustomLoaderItem()
    } else if (state.hasError) {
        state.error?.let {
            ErrorDataItem(message = it.localizedMessage ?: "")
        }
    } else {
        if (state.hasUsers) {
            ContactListData(state.userList, modifier, onLastItemReached, onUserClick)
        } else {
            EmptyDataItem(message = "No users data")
        }
    }
}

@Composable
private fun ContactListData(
    userList: List<User>,
    modifier: Modifier = Modifier,
    onLastItemReached: () -> Unit,
    onUserClick: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        ) {
            items(userList) { user ->
                ContactItem(user = user,
                    onUserClick = {
                        onUserClick(user.id)
                    })
                Divider(
                    color = Color(0xFF8E8E93),
                    modifier = Modifier
                        .height(0.5.dp)
                        .padding(68.dp, 0.dp, 0.dp, 0.dp)
                        .align(Alignment.BottomEnd)
                )
            }
            //When we reach the end of the lazycolumn que add a launch effect
            // that request to load more items
            item {
                LaunchedEffect(true) {
                    //onLastItemReached()
                }
            }
            //TODO: Implemented in screen loader when loading more items
        }
    }
}


@Preview
@Composable
private fun ContactListScreenPreview() {
    ContactListLayout(
        state = ContactListUiState(),
        onUserClick = {

        },
        onLastItemReached = {

        })
}