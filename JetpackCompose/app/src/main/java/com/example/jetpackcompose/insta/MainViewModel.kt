package com.example.jetpackcompose.insta

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _isFollowing = MutableLiveData<Boolean>(false)
    val isFollowing: LiveData<Boolean> = _isFollowing

    fun changeFollowing() {
        Log.d("MY", "change isFollowing = ${isFollowing.value}")
        val wasFollowing: Boolean = _isFollowing.value ?: false
        _isFollowing.value = !wasFollowing
        Log.d("MY", "change isFollowing = ${_isFollowing.value}")
    }
}