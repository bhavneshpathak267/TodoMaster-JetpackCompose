package com.example.todomaster.domain.usecase

import com.example.todomaster.domain.model.Task
import com.example.todomaster.domain.repository.TaskRepository

class AddTaskUseCase(
    private val repository: TaskRepository
) {
    @Throws(IllegalArgumentException::class)
    suspend operator fun invoke(task: Task) {
        if (task.title.isBlank()) {
            throw IllegalArgumentException("The title of the task cannot be empty.")
        }
        repository.insertTask(task)
    }
}