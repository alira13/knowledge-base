package com.example.jetpackcompose.vknews.view

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsNavigation(NewsContent: @Composable () -> Unit) {
    val items = listOf(
        ScreenNavigationItem.MainItem,
        ScreenNavigationItem.FavoriteItem,
        ScreenNavigationItem.ProfileItem
    )

    val scope = rememberCoroutineScope()

    // state всегда нужно оборачивать в remember, чтобы он пережил рекомпозицию
    // нужно обращать внимание, в каком месте используется state
    // и какая именно функция будет перерисовываться

    val stateChecked = remember { mutableIntStateOf(0) }
    val hostState = remember { SnackbarHostState() }
    val snackbarState = remember { mutableStateOf(true) }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = hostState) },

        floatingActionButton = {
            if (snackbarState.value) {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            val action = hostState.showSnackbar(
                                "This is snackbar",
                                actionLabel = "Hide"
                            )
                            if (action == SnackbarResult.ActionPerformed) {
                                snackbarState.value = false
                            } else snackbarState.value = true
                        }
                    },
                ) {
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                }
            }
        },
        bottomBar = {
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
        })
    {
        NewsContent()
    }
}
