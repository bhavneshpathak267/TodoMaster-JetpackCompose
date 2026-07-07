package com.example.todomaster.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: TodoDatabase? = null

    fun getDatabase(context: Context): TodoDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                TodoDatabase::class.java,
                "todo_database"
            )
                .fallbackToDestructiveMigration(false)
                .build()

            INSTANCE = instance

            instance
        }
    }
}