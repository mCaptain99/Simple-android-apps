package com.example.todolist

import androidx.room.*
import java.io.Serializable
import java.util.*

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val description: String,
    @TypeConverters(CalendarConverter::class) val date: Calendar,
    val time: Int,
    @TypeConverters(PriorityConverter::class) val priority: Priority,
    @TypeConverters(TaskTypeConverter::class) val type: TaskType
) : Serializable {
}