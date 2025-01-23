package com.example.jetpackcompose.inList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.inList.theme.JetpackComposeTheme
import com.example.jetpackcompose.inList.uiKit.BigText

@Composable
fun AuthScreen() {

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
            .fillMaxSize()
    ) {
        val text = "Enter your phone or email"

        IconButton(onClick = { /* Handle click */ }, modifier = Modifier.size(48.dp)) {
            Box(
                modifier = Modifier.fillMaxSize(), // Ensures the Box takes the entire space of IconButton
                contentAlignment = Alignment.CenterStart // Change alignment to your desired position
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = "Favorite Icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp),// Set your desired color here
                )
            }
        }

        BigText(text)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),

            shape = RoundedCornerShape(12.dp),
            colors = CardColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.primary
            ),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondaryContainer
            )
        ) { }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Button(onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),

                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                content = {
                    Text(text = "Use email", style = MaterialTheme.typography.labelMedium)
                })

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {},
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),

                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                content = {
                    Text(text = "Next", style = MaterialTheme.typography.labelMedium)
                })
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewAuthScreen() {
    JetpackComposeTheme(dynamicColor = false) {
        AuthScreen()
    }
}