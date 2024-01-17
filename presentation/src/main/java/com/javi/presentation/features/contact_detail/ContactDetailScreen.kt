package com.javi.presentation.features.contact_detail

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnitType.Companion.Sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.javi.domain.model.User
import com.javi.presentation.R
import com.javi.presentation.components.ContactInfoItem
import com.javi.presentation.components.StatusBarColorComponent
import com.javi.presentation.features.contact_detail.viewmodel.ContactDetailUiState
import com.javi.presentation.features.contact_detail.viewmodel.ContactDetailViewModel
import com.javi.presentation.ui.theme.openSansFamily
import com.javi.presentation.ui.theme.oswaldFamily
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailScreen(
    navController: NavController,
    viewModel: ContactDetailViewModel = koinViewModel()
) {
    //Change status bar icon to dark
    StatusBarColorComponent(false)

    Box {
        Column {
            Image(
                painter = painterResource(id = R.drawable.contact_detail_background),
                contentDescription = "Contact Detail Background",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            viewModel.state.user?.let {
                ContactDetailLayout(it)
            }
        }

        //TODO: Implement padding with percentages of screen
        // because maybe the distance of the padding changes in different devices
        AsyncImage(
            model = viewModel.state.image,
            contentDescription = "User Detail Image",
            modifier = Modifier
                .padding(16.dp, 155.dp, 16.dp, 16.dp)
                .size(80.dp)
                .clip(CircleShape)
                .border(4.dp, Color.White, CircleShape)
        )

        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            ),
            title = {
                Text(
                    text = viewModel.state.userName.toUpperCase(Locale.current),
                    fontSize = 20.sp,
                    fontFamily = oswaldFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)
        )
    }
}

@Composable
private fun ContactDetailLayout(user: User, modifier: Modifier = Modifier) {
    Box {
        var isInEditMode by rememberSaveable { mutableStateOf(false) }
        Row(modifier = Modifier.align(Alignment.TopEnd)) {
            IconButton(
                onClick = {}
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_take_picture),
                    contentDescription = "Take Picture"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    isInEditMode = !isInEditMode
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "Edit Profile"
                )
            }
        }
        Column(modifier = modifier.padding(16.dp, 32.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            ContactInfoItem(
                hintText = stringResource(id = R.string.contact_detail_name_lastname),
                valueText = user.name,
                icon = R.drawable.ic_user,
                isInEditMode = isInEditMode
            ) {

            }
            Spacer(modifier = Modifier.height(16.dp))
            ContactInfoItem(
                hintText = stringResource(id = R.string.contact_detail_email),
                valueText = user.email,
                icon = R.drawable.ic_mail,
                isInEditMode = isInEditMode
            ) {

            }
            Spacer(modifier = Modifier.height(16.dp))
            ContactInfoItem(
                hintText = stringResource(id = R.string.contact_detail_gender),
                valueText = getGenderString(user.gender),
                icon = R.drawable.ic_gender,
                isInEditMode = isInEditMode
            ) {

            }
            Spacer(modifier = Modifier.height(16.dp))
            ContactInfoItem(
                hintText = stringResource(id = R.string.contact_detail_register_date),
                valueText = user.registerDate,
                icon = R.drawable.ic_calendar,
                isInEditMode = isInEditMode
            ) {

            }
            Spacer(modifier = Modifier.height(16.dp))
            ContactInfoItem(
                hintText = stringResource(id = R.string.contact_detail_phone),
                valueText = user.phone,
                icon = R.drawable.ic_phone,
                isInEditMode = isInEditMode
            ) {

            }
        }
    }
}

@Composable
private fun getGenderString(gender: String): String {
    return when (gender) {
        User.MALE -> {
            stringResource(id = R.string.contact_male)
        }

        User.FEMALE -> {
            stringResource(id = R.string.contact_female)
        }

        else -> {
            ""
        }
    }
}

@Preview
@Composable
private fun ContactDetailScreenPreview() {
    ContactDetailLayout(User.sampleUser())
}