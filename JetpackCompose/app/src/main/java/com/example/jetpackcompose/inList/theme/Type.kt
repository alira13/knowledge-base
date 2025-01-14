package com.example.jetpackcompose.inList.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


// Set of Material typography styles to start with
val Typography = Typography(
    //header
    titleMedium = TextStyle(
        fontFamily = manropeFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),

    //large text
    bodyLarge = TextStyle(
        fontFamily = manropeFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),

    //text
    bodyMedium = TextStyle(
        fontFamily = manropeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    //listItem
    bodySmall = TextStyle(
        fontFamily = manropeFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    //button text
    labelMedium = TextStyle(
        fontFamily = manropeFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),

    //navigation
    labelSmall = TextStyle(
        fontFamily = manropeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )
)