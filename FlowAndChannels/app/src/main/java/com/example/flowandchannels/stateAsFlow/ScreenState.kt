package com.example.flowandchannels.stateAsFlow

sealed interface ScreenState {
    data object Initial : ScreenState
    data object Loading : ScreenState
    data object NotFound : ScreenState
    class DefinitionsLoaded(val definition: List<String>) : ScreenState
}