package com.example.jetpackcompose.vknews.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MainScreen() {
    val items = listOf(
        NavigationItem.MainItem,
        NavigationItem.FavoriteItem,
        NavigationItem.ProfileItem
    )
    val stateChecked = remember { mutableIntStateOf(0) }

    Scaffold(bottomBar = {
        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = index == stateChecked.intValue,
                    onClick = { stateChecked.intValue = index },
                    icon = {
                        Icon(
                            item.icon,
                            contentDescription = (stringResource(item.iconContentDescId))
                        )
                    },
                    label = { Text(stringResource(item.titleResId)) }
                )
            }
        }
    }) {
        Text("Content", modifier = Modifier.padding(it))
    }
}