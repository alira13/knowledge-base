package com.example.jetpackcompose.insta.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.insta.MainViewModel


@Composable
fun HeaderView(viewModel: MainViewModel) {
    val isFollowed = viewModel.isFollowing.observeAsState(false)

    Column {
        MenuView()
        StaticticView()
        ProfileButtonsView(isFollowed.value, viewModel)
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
private fun ProfileButtonsView(isFollowed: Boolean, viewModel: MainViewModel) {
    //переживает не только рекомпозицию, но и пересоздание фрагмента или activity

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    )
    {
        FollowButtonView(isFollowed) { viewModel.changeFollowing() }
        Spacer(modifier = Modifier.width(8.dp))
        ButtonView("Message", 1f, {})
        Spacer(modifier = Modifier.width(8.dp))
        ButtonView("+", 0.25f, {})
    }
}

@Composable
private fun RowScope.ButtonView(text: String, weight: Float, onClick: () -> Unit) {
    Button(shape = RoundedCornerShape(8.dp),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            disabledContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        modifier = Modifier.weight(weight),
        onClick = { onClick() }
    )
    {
        Text(
            text = text,
            fontSize = 10.sp
        )
    }
}

@Composable
private fun RowScope.FollowButtonView(isFollowed: Boolean, onClick: () -> Unit) {


    Button(
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isFollowed) {
                MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)
            } else {
                MaterialTheme.colorScheme.secondary
            },
            contentColor = MaterialTheme.colorScheme.onSecondary,
        ),
        modifier = Modifier.weight(1f),
        onClick = {
            onClick()
        }
    )
    {
        if (isFollowed)
            Text(
                text = "Following",
                fontSize = 10.sp
            )
        else {
            Text(
                text = "Follow",
                fontSize = 10.sp
            )
        }
    }
}

