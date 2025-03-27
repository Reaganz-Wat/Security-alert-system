package com.example.todoapp.viewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.local.database.MyAppDatabase
import com.example.todoapp.local.entities.AppSettingsEntity
import com.example.todoapp.local.entities.ContactEntity
import com.example.todoapp.local.entities.MessageMediaEntity
import com.example.todoapp.local.entities.MessageRecipientEntity
import com.example.todoapp.local.entities.SOSMessageEntity
import com.example.todoapp.local.entities.TimerEntity
import com.example.todoapp.local.entities.UserEntity
import com.example.todoapp.repository.MyAppRepository
import com.twilio.rest.chat.v1.service.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyviewModel(application: Application) : AndroidViewModel(application) {
    private val database = MyAppDatabase.getDatabase(application)
    private val repository = MyAppRepository(database)

    // Flows for different entities
    val allContacts: Flow<List<ContactEntity>> = repository.getAllContacts
    val allSOSMessages: Flow<List<SOSMessageEntity>> = repository.getAllSOSMessages
    val allUsers: Flow<List<UserEntity>> = repository.getAllUsers

    // User operations
    fun addUser(user: UserEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.addUser(user)
    }

    fun updateUser(user: UserEntity) = viewModelScope.launch {
        repository.updateUser(user)
    }

    fun getUserById(userId: Int) = viewModelScope.launch {
        repository.getUserById(userId)
    }

    suspend fun getUserByEmailAndPassword(email: String, password: String): UserEntity {
        return withContext(Dispatchers.IO){ repository.getUserByEmailAndPassword(email, password)!! }
    }

    // Contact operations
    fun addContact(contact: ContactEntity) = viewModelScope.launch {
        repository.addContact(contact)
    }

    fun updateContact(contact: ContactEntity) = viewModelScope.launch {
        repository.updateContact(contact)
    }

    fun getContactsByUserId(userId: Int) = viewModelScope.launch {
        repository.getContactsByUserId(userId)
    }

    // SOS Message operations
    fun addSOSMessage(sosMessage: SOSMessageEntity) = viewModelScope.launch {
        repository.addSOSMessage(sosMessage)
    }

    fun updateSOSMessage(sosMessage: SOSMessageEntity) = viewModelScope.launch {
        repository.updateSOSMessage(sosMessage)
    }

    fun getSOSMessagesByProfileId(profileId: Int) = viewModelScope.launch {
        repository.getSOSMessagesByProfileId(profileId)
    }

    // Message Media operations
    fun addMessageMedia(messageMedia: MessageMediaEntity) = viewModelScope.launch {
        repository.addMessageMedia(messageMedia)
    }

    fun updateMessageMedia(messageMedia: MessageMediaEntity) = viewModelScope.launch {
        repository.updateMessageMedia(messageMedia)
    }

    fun getMessageMediaByMessageId(messageId: Int) = viewModelScope.launch {
        repository.getMessageMediaByMessageId(messageId)
    }

    // Message Recipient operations
    fun addMessageRecipient(messageRecipient: MessageRecipientEntity) = viewModelScope.launch {
        repository.addMessageRecipient(messageRecipient)
    }

    fun updateMessageRecipient(messageRecipient: MessageRecipientEntity) = viewModelScope.launch {
        repository.updateMessageRecipient(messageRecipient)
    }

    fun getMessageRecipientsByMessageId(messageId: Int) = viewModelScope.launch {
        repository.getMessageRecipientsByMessageId(messageId)
    }

    // Timer operations
    fun addTimer(timer: TimerEntity) = viewModelScope.launch {
        repository.addTimer(timer)
    }

    fun updateTimer(timer: TimerEntity) = viewModelScope.launch {
        repository.updateTimer(timer)
    }

    fun getTimersByUserId(userId: Int) = viewModelScope.launch {
        repository.getTimersByUserId(userId)
    }

    // App Settings operations
    fun addAppSettings(appSettings: AppSettingsEntity) = viewModelScope.launch {
        repository.addAppSettings(appSettings)
    }

    fun updateAppSettings(appSettings: AppSettingsEntity) = viewModelScope.launch {
        repository.updateAppSettings(appSettings)
    }

    fun getAppSettingsByProfileId(profileId: Int) = viewModelScope.launch {
        repository.getAppSettingsByProfileId(profileId)
    }
}