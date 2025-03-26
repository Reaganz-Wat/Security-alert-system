package com.example.todoapp.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.local.entities.TimerEntity

// Timer DAO
@Dao
interface TimerDao {
    @Insert
    fun insert(timer: TimerEntity)

    @Update
    fun update(timer: TimerEntity)

    @Query("SELECT * FROM timers WHERE user_id = :userId")
    fun getTimersByUserId(userId: Int): List<TimerEntity>
}