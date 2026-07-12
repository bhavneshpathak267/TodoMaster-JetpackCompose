package com.example.todomaster.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todomaster.data.model.TaskEntity
import androidx.room.TypeConverters

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao
}