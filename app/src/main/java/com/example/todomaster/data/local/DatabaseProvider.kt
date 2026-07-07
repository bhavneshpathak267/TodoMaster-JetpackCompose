package com.example.todomaster.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var instance: TodoDatabase? = null

    fun provideDatabase(context: Context): TodoDatabase {
        return instance ?: synchronized(this) {
            val db = Room.databaseBuilder(
                context.applicationContext,
                TodoDatabase::class.java,
                "todo_master_db"
            ).build()
            instance = db
            db
        }
    }
}