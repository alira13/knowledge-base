package com.example.jetpackcompose.game.domain.useCase

import com.example.jetpackcompose.game.domain.entity.GameSettings
import com.example.jetpackcompose.game.domain.entity.Level
import com.example.jetpackcompose.game.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}