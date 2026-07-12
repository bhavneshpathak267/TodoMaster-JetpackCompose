package com.example.todomaster.data.mapper

import com.example.todomaster.data.model.FirestoreTask
import com.example.todomaster.domain.model.Task
import com.example.todomaster.domain.model.TaskCategory
import com.example.todomaster.domain.model.TaskPriority
import java.util.Date

fun Task.toFirestoreTask(userId: String): FirestoreTask {

    return FirestoreTask(

        cloudId = cloudId,

        userId = userId,

        title = title,

        description = description,

        isCompleted = isCompleted,

        priority = priority.name,

        category = category.name,

        createdAt = createdAt.time,

        dueDate = dueDate?.time,

        reminderTime = reminderTime?.time,

        notes = notes,

        color = color

    )

}

fun FirestoreTask.toTask(): Task {

    return Task(

        cloudId = cloudId,

        title = title,

        description = description,

        isCompleted = isCompleted,

        priority = try {
            TaskPriority.valueOf(priority)
        } catch (e: Exception) {
            TaskPriority.MEDIUM
        },

        category = try {
            TaskCategory.valueOf(category)
        } catch (e: Exception) {
            TaskCategory.PERSONAL
        },

        createdAt = Date(createdAt),

        dueDate = dueDate?.let { Date(it) },

        reminderTime = reminderTime?.let { Date(it) },

        notes = notes,

        color = color

    )

}