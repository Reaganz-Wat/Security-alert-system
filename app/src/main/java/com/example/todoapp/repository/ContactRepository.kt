package com.example.todoapp.repository

import com.example.todoapp.database.Contact
import com.example.todoapp.database.AppDatabase
import com.example.todoapp.database.ContactDao
import com.example.todoapp.database.Profile
import com.example.todoapp.database.ProfileDao
import kotlinx.coroutines.flow.Flow

class AppRepository(private val contactDao: ContactDao, private val profileDao: ProfileDao) {
    // Get all contacts
    val getAllContacts: Flow<List<Contact>> = contactDao.getAllContacts()
    // Get all profile
    val getProfileInfo: Flow<List<Profile>> = profileDao.readProfile()

    // Contact cruds
    suspend fun addContact(contact: Contact) = contactDao.addContact(contact)
    suspend fun updateContact(contact: Contact) = contactDao.updateContact(contact)
    suspend fun deleteContact(contact: Contact) = contactDao.deleteContact(contact)

    // Profile cruds
    suspend fun addProfile(profile: Profile) = profileDao.createProfile(profile)
    suspend fun updateProfile(profile: Profile) = profileDao.updateProfile(profile)
    suspend fun deleteProfile(profile: Profile) = profileDao.deleteProfile(profile)
}