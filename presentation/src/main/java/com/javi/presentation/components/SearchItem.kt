package com.javi.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.javi.presentation.R

@Composable
fun SearchItem(
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        label = {
            Text(text = stringResource(id = R.string.contact_list_search_hint))
        },
        placeholder = {
            Text(text = stringResource(id = R.string.contact_list_search_hint))
        },
        singleLine = true,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun SearchItemPreview() {
    SearchItem() {

    }
}