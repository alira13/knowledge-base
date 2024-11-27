package com.example.jetpackcompose.timesTable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                TimesTable()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimesTable() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        for (i in 1..9) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                for (j in 1..9) {
                    val color =
                        if ((i + j) % 2 == 0) {
                            Color.LightGray
                        } else {
                            Color.Transparent
                        }
                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.DarkGray
                            )
                            .fillMaxSize()
                            .weight(1f)
                            .background(color = color),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("${i * j}")
                    }
                }
            }
        }
    }
}