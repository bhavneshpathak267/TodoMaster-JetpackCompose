package com.example.todomaster.data.mapper

import com.example.todomaster.data.model.TaskEntity
import com.example.todomaster.domain.model.Category
import com.example.todomaster.domain.model.Priority
import com.example.todomaster.domain.model.Task
import com.example.todomaster.domain.model.TaskCategory
import com.example.todomaster.domain.model.TaskPriority

fun TaskEntity.toTask(): Task {
    return Task(
        id = id,
        cloudId = cloudId,
        title = title,
        description = description,
        isCompleted = isCompleted,
        priority = TaskPriority.valueOf(priority.name),
        category = TaskCategory.valueOf(category.name),
        createdAt = createdAt,
        dueDate = dueDate,
        reminderTime = reminderTime,
        notes = notes,
        color = color,
    )
}

fun Task.toTaskEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        cloudId = cloudId,
        title = title,
        description = description,
        isCompleted = isCompleted,
        priority = Priority.valueOf(priority.name),
        category = Category.valueOf(category.name),
        createdAt = createdAt,
        dueDate = dueDate,
        reminderTime = reminderTime,
        notes = notes,
        color = color,
    )
}
