package com.javi.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.javi.presentation.R

//TODO: Missing SF Pro because its private?

val openSansFamily = FontFamily(
    Font(R.font.opensans_bold, weight = FontWeight.Bold),
    Font(R.font.opensans_medium, weight = FontWeight.Medium),
    Font(R.font.opensans_regular, weight = FontWeight.Normal)
)

val oswaldFamily = FontFamily(
    Font(R.font.oswald_bold, weight = FontWeight.Bold),
    Font(R.font.oswald_medium, weight = FontWeight.Medium),
    Font(R.font.oswald_regular, weight = FontWeight.Normal)
)

val Typography = Typography()