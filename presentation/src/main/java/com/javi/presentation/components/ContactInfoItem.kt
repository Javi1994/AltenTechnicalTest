package com.javi.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.javi.presentation.R

@Composable
fun ContactInfoItem(
    hintText: String,
    valueText: String,
    icon: Int = R.drawable.ic_user,
    isInEditMode: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "Contact Info Icon",
            modifier = Modifier
                .align(CenterVertically)
                .size(32.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = valueText,
            readOnly = !isInEditMode,
            label = {
                Text(text = hintText)
            },
            placeholder = {
                Text(text = valueText)
            },
            singleLine = true,
            onValueChange = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterVertically)
        )
    }
}

@Preview
@Composable
private fun ContactInfoItemPreview() {
    ContactInfoItem(
        "Nombre y apellidos",
        "Laura Navarro Martinez",
    )
}