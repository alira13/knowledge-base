package com.example.jetpackcompose.inList

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.inList.theme.JetpackComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme(dynamicColor = false) {
                WelcomeScreen()
            }
        }
    }
}

@Composable
fun Test(name: String, modifier: Modifier = Modifier) {
    ShowScaffold()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowScaffold() {
    val bottomItems = listOf("Lists", "Current list", "Settings")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Title") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                })
        },
        bottomBar = {
            NavigationBar {
                bottomItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        label = { Text(item) },
                        selected = false,
                        onClick = {},
                        icon = { Icon(Icons.Filled.Settings, contentDescription = null) }
                    )
                }
            }
        },

        ) {
        Text(
            text = "This is scaffold content",
            modifier = Modifier.padding(it)
        )
    }

    ShowNavigationDrawer()

}

@Composable
fun ShowNavigationDrawer() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet() {
                Column {
                    NavigationDrawerItem(
                        label = { Text("Home") },
                        selected = false,
                        onClick = {},
                        icon = { Icon(Icons.Filled.Home, null) })

                    NavigationDrawerItem(
                        label = { Text("Settings") },
                        selected = false,
                        onClick = {},
                        icon = { Icon(Icons.Filled.Settings, null) })
                }
            }

        },
        drawerState = drawerState
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeTheme(dynamicColor = false) {
       WelcomeScreen()
    }
}