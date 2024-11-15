package com.example.dagger2.di_full_project.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val viewModelMap: @JvmSuppressWildcards Map<String, ViewModel>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelMap[modelClass.simpleName.toString()] as T
    }
}