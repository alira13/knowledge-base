package com.example.flowandchannels.finalFlowExample

sealed interface ScreenState {
    data object Initial : ScreenState
    data object Loading : ScreenState
    data object NotFound : ScreenState
    data class Error(val errorMessage: String) : ScreenState
    class DefinitionsLoaded(val definition: List<String>) : ScreenState
}