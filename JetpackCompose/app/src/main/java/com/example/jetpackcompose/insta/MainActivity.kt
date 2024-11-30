package com.example.jetpackcompose.insta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.insta.ui.theme.JetpackComposeTheme
import com.example.jetpackcompose.insta.view.HeaderView
import com.example.jetpackcompose.insta.view.PostsView
import com.example.jetpackcompose.insta.view.StoriesView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme(dynamicColor = false) {
                ProfileView()
            }
        }
    }


    @Composable
    fun ProfileView() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            HeaderView()
            StoriesView()
            PostsView()
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun PreviewLightOutCardView() {
        JetpackComposeTheme(darkTheme = false, dynamicColor = false) {
            ProfileView()
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun PreviewDarkOutCardView() {
        JetpackComposeTheme(darkTheme = true, dynamicColor = false) {
            ProfileView()
        }
    }
}

