package com.javi.presentation.contact_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.javi.presentation.contact_detail.viewmodel.ContactDetailUiState
import com.javi.presentation.contact_detail.viewmodel.ContactDetailViewModel
import com.javi.presentation.contact_list.viewmodel.ContactListViewModel
import com.javi.presentation.navigation.USER_ID_PARAM
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactDetailScreen(
    navController: NavController,
    viewModel: ContactDetailViewModel = koinViewModel()
) {
    ContactDetailLayout(state = viewModel.state)
}

@Composable
private fun ContactDetailLayout(state: ContactDetailUiState) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = state.user?.name ?: "",
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun ContactDetailScreenPreview() {
    ContactDetailLayout(ContactDetailUiState())
}