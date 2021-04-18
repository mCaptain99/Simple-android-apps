package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>
    @Insert
    fun insertAll(vararg tasks: Task)
    @Delete
    fun delete(tasks: Task)
}