package com.example.todomaster.domain.usecase

import com.example.todomaster.domain.repository.TaskRepository

class SyncFromCloudUseCase(
    private val repository: TaskRepository
) {

    suspend operator fun invoke() {
        repository.syncFromCloud()
    }

}