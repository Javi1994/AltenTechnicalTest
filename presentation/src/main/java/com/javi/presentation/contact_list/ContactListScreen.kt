package com.javi.presentation.contact_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Button

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.javi.domain.model.User
import com.javi.presentation.ObserveAsEvents
import com.javi.presentation.R
import com.javi.presentation.components.ContactItem
import com.javi.presentation.components.CustomLoaderItem
import com.javi.presentation.components.EmptyDataItem
import com.javi.presentation.components.ErrorDataItem
import com.javi.presentation.components.SearchItem
import com.javi.presentation.components.StatusBarColorComponent
import com.javi.presentation.contact_list.viewmodel.ContactListNavigationEvent
import com.javi.presentation.contact_list.viewmodel.ContactListUiEvent
import com.javi.presentation.contact_list.viewmodel.ContactListUiState
import com.javi.presentation.contact_list.viewmodel.ContactListViewModel
import com.javi.presentation.navigation.Screen
import com.javi.presentation.ui.theme.DimGrey
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(
    navController: NavController,
    viewModel: ContactListViewModel = koinViewModel()
) {
    //Change status bar icon to light
    StatusBarColorComponent(true)
    ObserveAsEvents(viewModel.navigationEventsChannelFlow) { event ->
        when (event) {
            is ContactListNavigationEvent.NavigateToUserDetail -> {
                navController.navigate("${Screen.ContactDetailScreen.route}/${event.userId}")
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                Text(
                    text = stringResource(id = R.string.contact_list_title).toUpperCase(
                        Locale.current
                    )
                )
            })
        },
        modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp)
    ) { paddingValues ->
        ContactListLayout(
            state = viewModel.state,
            onUserClick = {
                viewModel.onEvent(ContactListUiEvent.OnUserClick(it))
            },
            onLastItemReached = {
                viewModel.onEvent(ContactListUiEvent.OnLoadMoreUsers)
            },
            onSearchUser = {
                viewModel.onEvent(ContactListUiEvent.OnSearchUser(it))
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
    onUserClick: (String) -> Unit,
    onSearchUser: (String) -> Unit,
) {
    if (state.isLoading) {
        CustomLoaderItem()
    } else if (state.hasError) {
        state.error?.let {
            ErrorDataItem(
                message = it.localizedMessage
                    ?: stringResource(id = R.string.contact_list_undefined_error)
            )
        }
    } else {
        if (state.hasUsers) {
            ContactListData(state, modifier, onLastItemReached, onUserClick, onSearchUser)
        } else {
            EmptyDataItem(message = stringResource(id = R.string.contact_list_empty_users))
        }
    }
}

@Composable
private fun ContactListData(
    state: ContactListUiState,
    modifier: Modifier = Modifier,
    onLastItemReached: () -> Unit,
    onUserClick: (String) -> Unit,
    onSearchUser: (String) -> Unit,
) {
    val userList = if (state.isSearching) state.filteredUserList else state.userList
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            item {
                SearchItem {
                    onSearchUser(it)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(userList) { user ->
                ContactItem(user = user,
                    onUserClick = {
                        onUserClick(user.id)
                    })
                Divider(
                    color = DimGrey,
                    modifier = Modifier
                        .height(0.5.dp)
                        .padding(68.dp, 0.dp, 0.dp, 0.dp)
                        .align(Alignment.BottomEnd)
                )
            }

            if (!state.isSearching) {
                item {
                    //When we reach the end of the lazycolumn que add a launch effect
                    // that request to load more items
                    LaunchedEffect(true) {
                        onLastItemReached()
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun ContactListScreenPreview() {

}