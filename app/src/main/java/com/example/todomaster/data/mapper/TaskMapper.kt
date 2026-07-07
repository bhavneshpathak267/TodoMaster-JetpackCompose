package com.example.todomaster.data.mapper

import com.example.todomaster.data.model.TaskEntity
import com.example.todomaster.domain.model.Task
import java.util.Date

fun TaskEntity.toTask(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
        createdAt = Date(createdAt),
        dueDate = dueDate?.let { Date(it) }
    )
}

fun Task.toTaskEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
        createdAt = createdAt.time,
        dueDate = dueDate?.time
    )
}