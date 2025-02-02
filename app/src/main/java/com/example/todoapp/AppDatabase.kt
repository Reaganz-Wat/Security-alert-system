package com.example.todoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Contacts::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    // Define DAOs
    abstract class userDao(): UserDao
    abstract class contactDao(): ContactsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() // Allows clearing data when schema changes
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}