package com.example.todomaster.domain.usecase

import com.example.todomaster.domain.model.Task
import com.example.todomaster.domain.repository.TaskRepository

class AddTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        repository.insertTask(task)
    }
}