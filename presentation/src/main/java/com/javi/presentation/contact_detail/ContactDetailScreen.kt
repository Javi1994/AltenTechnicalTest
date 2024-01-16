package com.javi.presentation.contact_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.javi.presentation.R
import com.javi.presentation.components.ContactInfoItem
import com.javi.presentation.contact_detail.viewmodel.ContactDetailUiState
import com.javi.presentation.contact_detail.viewmodel.ContactDetailViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(
    navController: NavController,
    viewModel: ContactDetailViewModel = koinViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = viewModel.state.userName) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        ContactDetailLayout(
            state = viewModel.state,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun ContactDetailLayout(state: ContactDetailUiState, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        ContactInfoItem(
            hintText = stringResource(id = R.string.contact_detail_name_lastname),
            valueText = state.userName,
            icon = R.drawable.ic_user
        )
        Spacer(modifier = Modifier.height(16.dp))
        ContactInfoItem(
            hintText = stringResource(id = R.string.contact_detail_email),
            valueText = state.email,
            icon = R.drawable.ic_mail
        )
        Spacer(modifier = Modifier.height(16.dp))
        ContactInfoItem(
            hintText = stringResource(id = R.string.contact_detail_gender),
            valueText = state.gender,
            icon = R.drawable.ic_gender
        )
        Spacer(modifier = Modifier.height(16.dp))
        ContactInfoItem(
            hintText = stringResource(id = R.string.contact_detail_register_date),
            valueText = state.registerDate,
            icon = R.drawable.ic_calendar
        )
        Spacer(modifier = Modifier.height(16.dp))
        ContactInfoItem(
            hintText = stringResource(id = R.string.contact_detail_phone),
            valueText = state.phone,
            icon = R.drawable.ic_phone
        )
    }
}

@Preview
@Composable
private fun ContactDetailScreenPreview() {
    ContactDetailLayout(ContactDetailUiState())
}