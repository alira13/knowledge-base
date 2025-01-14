package com.example.jetpackcompose.inList

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.inList.theme.JetpackComposeTheme
import java.util.Locale


@Composable
fun WelcomeScreen() {
    val appName = "Grocery list"
    val welcomeText = "Welcome to your ${appName.lowercase(Locale.ROOT)}"
    val description = "Get started by adding items you need"
    val itemsDescription = "Add items"

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
            .fillMaxSize()

    ) {
        Header(appName)
        Welcome(welcomeText)
        Description(description)
        ListItem(itemsDescription)
        Spacer(modifier = Modifier.weight(1f))
        BottomButton()
    }
}

@Composable
fun Header(text: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp),
        contentAlignment = Alignment.Center,
        content = {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium
            )
        })
}

@Composable
fun Welcome(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun Description(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun ListItem(text: String) {
    for (i in 0..2) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .border(
                        width = 0.dp, shape = RectangleShape, color = Color.Gray
                    )
                    .background(color = Color.Gray)
            )

            Text(
                text = text,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun BottomButton() {
    Button(onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 0.dp),

        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        content = {
            Text(text = "Start shopping", style = MaterialTheme.typography.labelMedium)
        })

}

@Composable
@PreviewLightDark()
fun PreviewWelcomeScreen() {
    JetpackComposeTheme(dynamicColor = false) {
        WelcomeScreen()
    }
}
