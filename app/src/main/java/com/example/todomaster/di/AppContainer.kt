package com.example.todomaster.di

import android.content.Context
import com.example.todomaster.data.local.DatabaseProvider
import com.example.todomaster.data.repository.TaskRepositoryImpl
import com.example.todomaster.domain.repository.TaskRepository
import com.example.todomaster.domain.usecase.*

class AppContainer(private val context: Context) {

    private val database by lazy {
        DatabaseProvider.provideDatabase(context)
    }

    private val repository: TaskRepository by lazy {
        TaskRepositoryImpl(database.taskDao)
    }

    val taskUseCases: TaskUseCases by lazy {
        TaskUseCases(
            getTasks = GetTasksUseCase(repository),
            addTask = AddTaskUseCase(repository),
            updateTask = UpdateTaskUseCase(repository),
            deleteTask = DeleteTaskUseCase(repository)
        )
    }
}