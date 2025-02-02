package com.example.todoapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo("contact") val contact: String,
    @ColumnInfo("address") val address: String
)

@Entity
data class Contacts (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "contact") val contact: String,
    @ColumnInfo(name = "isActive") val isActive: Boolean
)