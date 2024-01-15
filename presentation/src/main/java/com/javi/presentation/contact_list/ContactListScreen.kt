package com.javi.presentation.contact_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.javi.presentation.navigation.Screen

@Composable
fun ContactListScreen(navController: NavController) {
    ContactListLayout(
        onDetailClick = {
            navController.navigate(Screen.ContactDetailScreen.route)
        }
    )
}

@Composable
private fun ContactListLayout(
    onDetailClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(alignment = Alignment.Center)) {
            Text(
                text = "Contact List Screen",
            )
            Button(onClick = { onDetailClick() }) {
                Text(text = "Go to Detail Screen")
            }
        }
    }
}

@Preview
@Composable
private fun ContactListScreenPreview() {
    ContactListLayout(onDetailClick = {

    })
}