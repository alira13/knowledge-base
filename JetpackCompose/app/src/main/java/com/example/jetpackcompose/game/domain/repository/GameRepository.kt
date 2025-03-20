package com.example.jetpackcompose.game.domain.repository

import com.example.jetpackcompose.game.domain.entity.GameSettings
import com.example.jetpackcompose.game.domain.entity.Level
import com.example.jetpackcompose.game.domain.entity.Question

interface GameRepository {
    fun generateQuestion(maxSumValue:Int, countOfOption:Int): Question
    fun getGameSettings(level: Level): GameSettings
}