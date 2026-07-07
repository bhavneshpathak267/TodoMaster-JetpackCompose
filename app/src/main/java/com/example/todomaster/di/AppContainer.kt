package com.example.todomaster.di

import android.content.Context
import com.example.todomaster.data.local.DatabaseProvider
import com.example.todomaster.data.repository.TaskRepositoryImpl
import com.example.todomaster.domain.usecase.AddTaskUseCase
import com.example.todomaster.domain.usecase.DeleteTaskUseCase
import com.example.todomaster.domain.usecase.GetTasksUseCase
import com.example.todomaster.domain.usecase.UpdateTaskUseCase

class AppContainer(context: Context) {

    // Database
    private val database = DatabaseProvider.getDatabase(context)

    // DAO
    private val taskDao = database.taskDao()

    // Repository
    private val repository = TaskRepositoryImpl(taskDao)

    // Use Cases
    val addTaskUseCase = AddTaskUseCase(repository)
    val getTasksUseCase = GetTasksUseCase(repository)
    val updateTaskUseCase = UpdateTaskUseCase(repository)
    val deleteTaskUseCase = DeleteTaskUseCase(repository)
}