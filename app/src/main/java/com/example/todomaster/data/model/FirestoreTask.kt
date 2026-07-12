package com.example.todomaster.data.model

data class FirestoreTask(
    val cloudId: String = "",
    val userId: String = "",
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
    val priority: String = "MEDIUM",
    val category: String = "PERSONAL",
    val createdAt: Long = 0L,
    val dueDate: Long? = null,
    val reminderTime: Long? = null,
    val notes: String = "",
    val color: Long = 0xFF6750A4
)
