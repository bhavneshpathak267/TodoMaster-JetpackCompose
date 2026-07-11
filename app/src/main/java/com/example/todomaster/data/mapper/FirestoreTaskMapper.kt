package com.example.todomaster.data.mapper

import com.example.todomaster.data.remote.FirestoreTask
import com.example.todomaster.domain.model.Task
import java.util.Date

fun Task.toFirestoreTask(userId: String): FirestoreTask {

    return FirestoreTask(

        id = id,

        title = title,

        description = description,

        isCompleted = isCompleted,

        createdAt = createdAt.time,

        dueDate = dueDate?.time,

        userId = userId

    )

}

fun FirestoreTask.toTask(): Task {

    return Task(

        id = id,

        title = title,

        description = description,

        isCompleted = isCompleted,

        createdAt = Date(createdAt),

        dueDate = dueDate?.let { Date(it) }

    )

}