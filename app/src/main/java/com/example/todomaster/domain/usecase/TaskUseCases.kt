package com.example.todomaster.domain.usecase

data class TaskUseCases(

    val addTask: AddTaskUseCase,

    val getTasks: GetTasksUseCase,

    val updateTask: UpdateTaskUseCase,

    val deleteTask: DeleteTaskUseCase
)