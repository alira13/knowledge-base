package com.example.jetpackcompose.game.presentation.viewModel

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackcompose.game.data.GameRepositoryImpl
import com.example.jetpackcompose.game.domain.entity.GameSettings
import com.example.jetpackcompose.game.domain.entity.Level
import com.example.jetpackcompose.game.domain.entity.Question
import com.example.jetpackcompose.game.domain.repository.GameRepository
import com.example.jetpackcompose.game.domain.useCase.GenerateQuestionUseCase
import com.example.jetpackcompose.game.domain.useCase.GetGameSettingsUseCase

class GameViewModel(val context: Application, private val level: Level) : AndroidViewModel(context) {
    private val repository: GameRepository = GameRepositoryImpl
    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)

    private lateinit var settings: GameSettings
    private lateinit var timer: CountDownTimer
    private var rightAnswersCount = 0
    private var questionsCount = 0
    private var percentOfRightAnswers = 0

    val formattedTime: LiveData<String>
        get() = _formattedTime
    private val _formattedTime = MutableLiveData<String>()

    val question: LiveData<Question>
        get() = _question
    private val _question = MutableLiveData<Question>()

    val progressAnswers: LiveData<Int>
        get() = _progress
    private val _progress = MutableLiveData<Int>()

    init {
        getSettings()
        startTimer()
        generateQuestion()
    }

    private fun getSettings() {
        this.settings = getGameSettingsUseCase(level)
    }

    private fun startTimer() {
        timer = object :
            CountDownTimer(settings.gameTimeInSeconds / MILLIS_IN_SECONDS, MILLIS_IN_SECONDS) {

            override fun onTick(millisUntilFinished: Long) {
                _formattedTime.value = formatTime(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }
        }
        timer.start()
    }

    private fun formatTime(millisUntilFinished: Long): String {
        val totalSeconds = millisUntilFinished / MILLIS_IN_SECONDS
        val minutes = totalSeconds / MINUTES_IN_SECONDS
        val seconds = totalSeconds - minutes * MINUTES_IN_SECONDS

        return String.format("%2d:%2d", minutes, seconds)
    }

    private fun finishGame() {
    }

    private fun generateQuestion() {
        _question.value = generateQuestionUseCase(settings.maxSumValue)
    }


    fun chooseAnswer(choseAnswer: Int) {
        checkAnswer(choseAnswer)
        generateQuestion()
    }

    private fun checkAnswer(choseAnswer: Int) {
        val rightAnswer = _question.value?.rightAnswer
        if (choseAnswer == rightAnswer) {
            rightAnswersCount++
        }
        questionsCount++
    }


    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    companion object {
        const val MILLIS_IN_SECONDS = 1000L
        const val MINUTES_IN_SECONDS = 60L
    }
}