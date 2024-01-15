package com.javi.presentation.contact_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button

import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.javi.presentation.contact_list.viewmodel.ContactListUiState
import com.javi.presentation.contact_list.viewmodel.ContactListViewModel
import com.javi.presentation.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactListScreen(
    navController: NavController,
    viewModel: ContactListViewModel = koinViewModel()
) {
    ContactListLayout(
        state = viewModel.state,
        onDetailClick = {
            navController.navigate(Screen.ContactDetailScreen.route)
        }
    )
}

@Composable
private fun ContactListLayout(
    state: ContactListUiState,
    onDetailClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(state.userList) {
                Text(
                    text = it.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onDetailClick()
                        })
            }
        }
    }
}

@Preview
@Composable
private fun ContactListScreenPreview() {
    ContactListLayout(
        state = ContactListUiState(),
        onDetailClick = {

        })
}