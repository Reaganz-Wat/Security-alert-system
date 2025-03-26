package com.example.todoapp.repository


import com.example.todoapp.database.AppDatabase
import com.example.todoapp.local.database.MyAppDatabase
import com.example.todoapp.local.entities.AppSettingsEntity
import com.example.todoapp.local.entities.ContactEntity
import com.example.todoapp.local.entities.MessageMediaEntity
import com.example.todoapp.local.entities.MessageRecipientEntity
import com.example.todoapp.local.entities.SOSMessageEntity
import com.example.todoapp.local.entities.TimerEntity
import com.example.todoapp.local.entities.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyAppRepository(
    private val database: MyAppDatabase
) {
    // User operations
    private val userDao = database.userDao()
//    val getAllUsers: Flow<List<UserEntity>> = flow {
//        emit(userDao.)
//    }

    suspend fun addUser(user: UserEntity) = userDao.insert(user)
    suspend fun updateUser(user: UserEntity) = userDao.update(user)
    suspend fun getUserById(userId: Int) = userDao.getUserById(userId)

    // Contact operations
    private val contactDao = database.contactDao()
    val getAllContacts: Flow<List<ContactEntity>> = flow {
        emit(contactDao.getContactsByUserId(userId = 1)) // Assuming a default user ID
    }

    suspend fun addContact(contact: ContactEntity) = contactDao.insert(contact)
    suspend fun updateContact(contact: ContactEntity) = contactDao.update(contact)
    suspend fun getContactsByUserId(userId: Int) = contactDao.getContactsByUserId(userId)

    // SOS Message operations
    private val sosMessageDao = database.sosMessageDao()
    val getAllSOSMessages: Flow<List<SOSMessageEntity>> = flow {
        emit(sosMessageDao.getSOSMessagesByProfileId(profileId = 1)) // Assuming a default profile ID
    }

    suspend fun addSOSMessage(sosMessage: SOSMessageEntity) = sosMessageDao.insert(sosMessage)
    suspend fun updateSOSMessage(sosMessage: SOSMessageEntity) = sosMessageDao.update(sosMessage)
    suspend fun getSOSMessagesByProfileId(profileId: Int) = sosMessageDao.getSOSMessagesByProfileId(profileId)

    // Message Media operations
    private val messageMediaDao = database.messageMediaDao()
    suspend fun addMessageMedia(messageMedia: MessageMediaEntity) = messageMediaDao.insert(messageMedia)
    suspend fun updateMessageMedia(messageMedia: MessageMediaEntity) = messageMediaDao.update(messageMedia)
    suspend fun getMessageMediaByMessageId(messageId: Int) = messageMediaDao.getMessageMediaByMessageId(messageId)

    // Message Recipient operations
    private val messageRecipientDao = database.messageRecipientDao()
    suspend fun addMessageRecipient(messageRecipient: MessageRecipientEntity) = messageRecipientDao.insert(messageRecipient)
    suspend fun updateMessageRecipient(messageRecipient: MessageRecipientEntity) = messageRecipientDao.update(messageRecipient)
    suspend fun getMessageRecipientsByMessageId(messageId: Int) = messageRecipientDao.getMessageRecipientsByMessageId(messageId)

    // Timer operations
    private val timerDao = database.timerDao()
    suspend fun addTimer(timer: TimerEntity) = timerDao.insert(timer)
    suspend fun updateTimer(timer: TimerEntity) = timerDao.update(timer)
    suspend fun getTimersByUserId(userId: Int) = timerDao.getTimersByUserId(userId)

    // App Settings operations
    private val appSettingsDao = database.appSettingsDao()
    suspend fun addAppSettings(appSettings: AppSettingsEntity) = appSettingsDao.insert(appSettings)
    suspend fun updateAppSettings(appSettings: AppSettingsEntity) = appSettingsDao.update(appSettings)
    suspend fun getAppSettingsByProfileId(profileId: Int) = appSettingsDao.getAppSettingsByProfileId(profileId)
}