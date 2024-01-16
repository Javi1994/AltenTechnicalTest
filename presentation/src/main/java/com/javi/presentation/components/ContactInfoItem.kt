package com.javi.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javi.presentation.R
import com.javi.presentation.ui.theme.LightGrey

@Composable
fun ContactInfoItem(
    hintText: String,
    valueText: String,
    icon: Int = R.drawable.ic_user,
    isInEditMode: Boolean = false,
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf(valueText) }
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
            value = text,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedLabelColor = LightGrey,
                unfocusedLabelColor = LightGrey,
            ),
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            readOnly = !isInEditMode,
            label = {
                Text(text = hintText)
            },
            placeholder = {
                Text(text = valueText, fontWeight = FontWeight.Bold)
            },
            singleLine = true,
            onValueChange = {
                text = it
                onValueChange(it)
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
    ) {

    }
}