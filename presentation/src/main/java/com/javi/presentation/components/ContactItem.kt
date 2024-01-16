package com.javi.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.F
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.javi.domain.model.User
import com.javi.presentation.R
import com.javi.presentation.ui.theme.DimGrey
import com.javi.presentation.ui.theme.openSansFamily
import com.javi.presentation.ui.theme.oswaldFamily

@Composable
fun ContactItem(user: User, onUserClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onUserClick() }
            .padding(0.dp, 0.dp, 0.dp, 16.dp)
    ) {
        Row(
            modifier = Modifier
                .height(52.dp)
        ) {
            AsyncImage(
                model = user.image,
                contentDescription = "User Image",
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.size(16.dp))

            Column(modifier = Modifier.align(CenterVertically)) {
                Text(
                    text = user.name,
                    fontSize = 16.sp,
                    color = Color.Black,
                    lineHeight = 21.sp,
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = user.email,
                    fontSize = 14.sp,
                    color = DimGrey,
                    fontFamily = openSansFamily,
                    lineHeight = 16.sp
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_arrow),
            contentDescription = "User Right Arrow",
            modifier = Modifier.align(CenterEnd)
        )
    }
}

@Preview
@Composable
private fun ContactItemPreview() {
    ContactItem(
        User.sampleUser()
    ) {

    }
}