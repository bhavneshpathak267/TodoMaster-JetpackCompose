package com.example.todomaster.domain.usecase

data class TaskUseCases(
    val getTasks: GetTasksUseCase,
    val addTask: AddTaskUseCase,
    val updateTask: UpdateTaskUseCase,
    val deleteTask: DeleteTaskUseCase,
    val syncFromCloud: SyncFromCloudUseCase
)