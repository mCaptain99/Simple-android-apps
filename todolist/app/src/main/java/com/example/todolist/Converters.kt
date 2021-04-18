package com.example.todolist
import androidx.room.TypeConverter
import java.util.*

class TaskTypeConverter {
    @TypeConverter
    fun fromType(type: TaskType) = type.image

    @TypeConverter
    fun fromInt(int: Int) = TaskType.values().find { it.image == int }
}

class CalendarConverter {
    @TypeConverter
    fun fromCalendar(calendar: Calendar) = calendar.timeInMillis

    @TypeConverter
    fun fromLong(long: Long) = Calendar.getInstance().apply { timeInMillis = long }
}

class PriorityConverter{
    @TypeConverter
    fun fromPriority(priority: Priority) = priority.ordering

    @TypeConverter
    fun fromInt(int: Int) = Priority.values().find { it.ordering == int }
}