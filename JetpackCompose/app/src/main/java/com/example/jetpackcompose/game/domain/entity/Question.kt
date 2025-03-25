package com.example.jetpackcompose.game.domain.entity

import kotlinx.coroutines.selects.selectUnbiased

data class Question(
    val sum: Int,
    val visibleNumber: Int,
    val options: List<Int>
) {
    val rightAnswer: Int
        get() = sum - visibleNumber
}