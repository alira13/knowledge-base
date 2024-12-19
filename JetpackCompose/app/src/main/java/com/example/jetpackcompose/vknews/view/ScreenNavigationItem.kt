package com.example.jetpackcompose.vknews.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpackcompose.R

sealed class ScreenNavigationItem(
    val titleResId: Int,
    val icon: ImageVector,
    val iconContentDescId: Int
) {
    data object MainItem :
        ScreenNavigationItem(
            titleResId = R.string.title_home,
            icon = Icons.Filled.Home,
            iconContentDescId = R.string.desc_icon_home
        )

    data object FavoriteItem :
        ScreenNavigationItem(
            titleResId = R.string.title_favorite,
            icon = Icons.Filled.Favorite,
            iconContentDescId = R.string.desc_icon_favorite
        )

    data object ProfileItem :
        ScreenNavigationItem(
            titleResId = R.string.title_profile,
            icon = Icons.Filled.Person,
            iconContentDescId = R.string.desc_icon_profile
        )
}