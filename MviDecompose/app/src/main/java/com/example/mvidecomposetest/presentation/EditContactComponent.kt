package com.example.mvidecomposetest.presentation

import kotlinx.coroutines.flow.StateFlow

interface EditContactComponent {

    val model: StateFlow<Model>

    fun onUsernameChanged(userName:String)

    fun onPhoneChanged(phone:String)

    fun onSaveContactClicked()

    data class Model(val userName: String, val phone: String)

}