package com.example.todoapp.repository

import com.example.todoapp.database.Contact
import com.example.todoapp.database.AppDatabase
import com.example.todoapp.database.ContactDao
import kotlinx.coroutines.flow.Flow

class AppRepository(private val contactDao: ContactDao) {
    // Get all contacts
    val getAllContacts: Flow<List<Contact>> = contactDao.getAllContacts()

    suspend fun addContact(contact: Contact) = contactDao.addContact(contact)

}