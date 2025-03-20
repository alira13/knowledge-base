package com.example.jetpackcompose.game.domain.useCase

import com.example.jetpackcompose.game.domain.entity.Question
import com.example.jetpackcompose.game.domain.repository.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {

    // так как у нас будет всего 1 метод, мы можем переопределить оператор invoke
    // и можем использовать generateQuestionUseCase() везде где нужно вызвать единственный метод useCase
    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    private companion object {
        const val COUNT_OF_OPTIONS = 6
    }
}