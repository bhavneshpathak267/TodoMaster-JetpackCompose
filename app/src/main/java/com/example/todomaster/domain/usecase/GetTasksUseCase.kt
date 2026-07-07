package com.example.todomaster.domain.usecase

import com.example.todomaster.domain.model.Task
import com.example.todomaster.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTasksUseCase(
    private val repository: TaskRepository
) {
    operator fun invoke(): Flow<List<Task>> {
        return repository.getTasks().map { tasks ->
            // Business rule: Show uncompleted tasks first, then sort by creation date
            tasks.sortedWith(
                compareBy<Task> { it.isCompleted }
                    .thenByDescending { it.createdAt }
            )
        }
    }
}