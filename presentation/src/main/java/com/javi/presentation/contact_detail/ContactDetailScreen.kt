package com.javi.presentation.contact_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun ContactDetailScreen(navController: NavController) {
    ContactDetailLayout()
}

@Composable
private fun ContactDetailLayout() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Contact Detail Screen",
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun ContactDetailScreenPreview() {
    ContactDetailLayout()
}