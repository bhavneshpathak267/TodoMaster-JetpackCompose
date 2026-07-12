package com.example.todomaster.data.local

import androidx.room.TypeConverter
import com.example.todomaster.domain.model.Category
import com.example.todomaster.domain.model.Priority
import java.util.Date

class Converters {

    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time

    @TypeConverter
    fun toDate(value: Long?): Date? =
        value?.let { Date(it) }

    @TypeConverter
    fun fromPriority(priority: Priority): String =
        priority.name

    @TypeConverter
    fun toPriority(value: String): Priority =
        Priority.valueOf(value)

    @TypeConverter
    fun fromCategory(category: Category): String =
        category.name

    @TypeConverter
    fun toCategory(value: String): Category =
        Category.valueOf(value)
}