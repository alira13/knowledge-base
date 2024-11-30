package com.example.jetpackcompose.insta.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R

@Preview
@Composable
fun HeaderView() {
    Column {
        MenuView()
        StaticticView()
        ProfileButtonsView()
    }
}

@Composable
private fun MenuView() {

}

@Composable
private fun StaticticView() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ProfilePictureView()
        UserStaticticsView(120, "posts")
        UserStaticticsView(211, "followers")
        UserStaticticsView(421, "following")
    }
}

@Composable
private fun UserStaticticsView(number: Int, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {
        Box()
        {
            Text(
                number.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                fontFamily = FontFamily.SansSerif
            )
        }
        Box()
        {
            Text(text, fontSize = 16.sp)

        }
    }
}

@Composable
private fun ProfilePictureView() {
    Image(
        painter = painterResource(R.drawable.ic_instagram),
        contentDescription = "Profile picture",
        modifier = Modifier
            .size(96.dp)
            .clip(shape = CircleShape)
            .background(
                color = MaterialTheme.colorScheme.secondary
            )

    )
}

@Composable
private fun ProfileButtonsView() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    )
    {
        ButtonView("Edit profile")
        ButtonView("Share profile")
        ButtonView("+")
    }
}

@Composable
private fun ButtonView(text: String) {
    Button(shape = RoundedCornerShape(8.dp),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            disabledContentColor = MaterialTheme.colorScheme.onSecondary
        ),

        onClick = { }
    )
    {
        Text(
            text = text,
            fontSize = 10.sp
        )
    }
}

