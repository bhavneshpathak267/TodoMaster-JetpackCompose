package com.example.todomaster.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todomaster.domain.model.Task
import com.example.todomaster.domain.usecase.TaskUseCases
import com.example.todomaster.ui.state.TaskUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState())

    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    init {
        getAllTasks()
    }

    private fun getAllTasks() {

        viewModelScope.launch {

            taskUseCases.getTasks().collect { taskList ->

                _uiState.update {
                    it.copy(tasks = taskList)
                }

            }

        }

    }

    fun addTask(task: Task) {

        viewModelScope.launch {
            taskUseCases.addTask(task)
        }

    }

    fun updateTask(task: Task) {

        viewModelScope.launch {
            taskUseCases.updateTask(task)
        }

    }

    fun deleteTask(task: Task) {

        viewModelScope.launch {
            taskUseCases.deleteTask(task)
        }

    }

}