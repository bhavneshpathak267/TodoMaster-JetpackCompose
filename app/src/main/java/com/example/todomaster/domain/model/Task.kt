package com.example.todomaster.domain.model

import java.util.Date

data class Task(

    val id: Int = 0,

    val cloudId: String = "",

    val title: String,

    val description: String,

    val isCompleted: Boolean = false,

    val priority: TaskPriority = TaskPriority.MEDIUM,

    val category: TaskCategory = TaskCategory.PERSONAL,

    val createdAt: Date = Date(),

    val dueDate: Date? = null,

    val reminderTime: Date? = null,

    val notes: String = "",

    val color: Long = 0xFF6750A4

)

enum class TaskPriority {
    LOW,
    MEDIUM,
    HIGH
}

enum class TaskCategory {
    PERSONAL,
    WORK,
    STUDY,
    HEALTH,
    SHOPPING,
    OTHER
}
