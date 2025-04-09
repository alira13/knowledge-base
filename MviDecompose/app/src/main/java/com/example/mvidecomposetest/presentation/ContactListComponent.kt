package com.example.mvidecomposetest.presentation

import com.example.mvidecomposetest.domain.Contact
import kotlinx.coroutines.flow.StateFlow

interface ContactListComponent {
    val model: StateFlow<Model>

    fun onAddContactClicked()

    fun onContactClicked(item: Contact)

    data class Model(val contacts: List<Contact>)
}