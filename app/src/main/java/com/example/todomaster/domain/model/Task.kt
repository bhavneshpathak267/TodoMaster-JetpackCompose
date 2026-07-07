package com.example.todomaster.domain.model

import java.util.Date

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val createdAt: Date = Date(),
    val dueDate: Date? = null
)