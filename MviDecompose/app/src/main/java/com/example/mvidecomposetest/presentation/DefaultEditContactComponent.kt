package com.example.mvidecomposetest.presentation

import com.example.mvidecomposetest.data.RepositoryImpl
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.domain.EditContactUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DefaultEditContactComponent(val contact: Contact) : EditContactComponent {

    private val repository = RepositoryImpl
    private val useCase = EditContactUseCase(repository)

    private val _model =
        MutableStateFlow(EditContactComponent.Model(contact.username, contact.phone))

    override val model: StateFlow<EditContactComponent.Model>
        get() = _model.asStateFlow()

    override fun onUsernameChanged(userName: String) {
        _model.value = _model.value.copy(userName = userName)
    }

    override fun onPhoneChanged(phone: String) {
        _model.value = _model.value.copy(phone = phone)
    }

    override fun onSaveContactClicked() {
        val (userName, phone) = _model.value
        useCase(contact.copy(username = userName, phone = phone))
    }
}