package com.example.todomaster.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todomaster.domain.model.Category
import com.example.todomaster.domain.model.Priority
import java.util.Date

@Entity(tableName = "tasks")
data class TaskEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val cloudId: String = "",

    val title: String,

    val description: String,

    val isCompleted: Boolean = false,

    val createdAt: Date = Date(),

    val dueDate: Date? = null,

    val priority: Priority = Priority.MEDIUM,

    val category: Category = Category.PERSONAL,

    val color: Long = 0xFF6750A4,

    val reminderTime: Date? = null,

    val notes: String = ""

)