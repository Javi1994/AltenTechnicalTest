package com.javi.presentation.contact_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ContactListScreen() {
    ContactListLayout()
}

@Composable
private fun ContactListLayout() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Contact List Screen",
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun ContactListScreenPreview() {
    ContactListLayout()
}