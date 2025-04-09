package com.example.mvidecomposetest.presentation

import com.example.mvidecomposetest.data.RepositoryImpl
import com.example.mvidecomposetest.domain.Contact
import com.example.mvidecomposetest.domain.GetContactsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DefaultContactListComponent(
    val onEditingContactRequested: (Contact) -> Unit,
    val onAddContactRequested: () -> Unit
) : ContactListComponent {
    val repository = RepositoryImpl
    val useCase = GetContactsUseCase(repository)

    private val coroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    private val _model = useCase().map {
        ContactListComponent.Model(it)
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = ContactListComponent.Model(listOf())
    )
    override val model: StateFlow<ContactListComponent.Model>
        get() = _model

    override fun onAddContactClicked() {
        onAddContactRequested()
    }

    override fun onContactClicked(contact: Contact) {
        onEditingContactRequested(contact)
    }
}