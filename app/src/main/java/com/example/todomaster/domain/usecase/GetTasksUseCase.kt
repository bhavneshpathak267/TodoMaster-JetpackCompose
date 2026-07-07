package com.example.todomaster.domain.usecase

import com.example.todomaster.domain.repository.TaskRepository

class GetTasksUseCase(
    private val repository: TaskRepository
) {
    operator fun invoke() = repository.getAllTasks()
}