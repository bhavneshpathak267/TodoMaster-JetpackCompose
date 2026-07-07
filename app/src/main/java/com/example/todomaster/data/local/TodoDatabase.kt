package com.example.todomaster.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todomaster.data.model.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}
