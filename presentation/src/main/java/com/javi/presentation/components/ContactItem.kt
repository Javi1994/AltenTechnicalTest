package com.javi.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.javi.domain.model.User

@Composable
fun ContactItem(user: User) {
    Column {

    }
}

@Preview
@Composable
private fun ContactItemPreview() {
    ContactItem(User())
}