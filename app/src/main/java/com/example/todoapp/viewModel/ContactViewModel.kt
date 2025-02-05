package com.example.todoapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.database.AppDatabase
import com.example.todoapp.database.Contact
import com.example.todoapp.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ContactViewModel(application: Application): AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val repository = AppRepository(database.contactDao())

    val allContacts: Flow<List<Contact>> = repository.getAllContacts

    fun addContacts(contact: Contact) = viewModelScope.launch {
        repository.addContact(contact)
    }

    fun updateContacts(contact: Contact) = viewModelScope.launch {
        repository.updateContact(contact)
    }

    fun deleteContacts(contact: Contact) = viewModelScope.launch {
        repository.deleteContact(contact)
    }

}