package com.example.todoapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Update
    fun updateUser(user: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM user WHERE uid = :userId")
    fun deleteById(userId: Int)
}

@Dao
interface ContactsDao {

    // Get all contacts
    @Query("SELECT * FROM contacts")
    fun getAllContacts(): List<Contacts>

    // Get a contact by ID
    @Query("SELECT * FROM contacts WHERE uid = :contactId")
    fun getContactById(contactId: Int): Contacts?

    // Insert a new contact
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: Contacts)

    // Insert multiple contacts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllContacts(vararg contacts: Contacts)

    // Update contact details
    @Update
    fun updateContact(contact: Contacts)

    // Delete a specific contact
    @Delete
    fun deleteContact(contact: Contacts)

    // Delete contact by ID
    @Query("DELETE FROM contacts WHERE uid = :contactId")
    fun deleteContactById(contactId: Int)

    // Count total contacts
    @Query("SELECT COUNT(*) FROM contacts")
    fun getContactsCount(): Int

    // Get only active contacts
    @Query("SELECT * FROM contacts WHERE isActive = 1")
    fun getActiveContacts(): List<Contacts>
}