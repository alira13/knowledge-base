package com.example.mvidecomposetest.presentation

import com.example.mvidecomposetest.data.RepositoryImpl
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.domain.EditContactUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultAddAddContactComponent : AddContactComponent {
    private val repository = RepositoryImpl
    private val useCase = EditContactUseCase(repository)

    private val _model = MutableStateFlow(AddContactComponent.Model("", ""))

    override val model: StateFlow<AddContactComponent.Model>
        get() = _model.asStateFlow()

    override fun onUsernameChanged(userName: String) {
        _model.value = _model.value.copy(userName)
    }

    override fun onPhoneChanged(phone: String) {
        _model.value = _model.value.copy(phone)
    }

    override fun onSaveContactClicked() {
        val (username, phone) = _model.value
        useCase(Contact(username = username, phone = phone))
    }
}