package com.example.todomaster.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todomaster.domain.model.Task
import com.example.todomaster.domain.usecase.TaskUseCases
import com.example.todomaster.ui.state.TaskUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(TaskUiState())
    val state: StateFlow<TaskUiState> = _state.asStateFlow()

    init {
        getTasks()
    }

    private fun getTasks() {
        _state.value = _state.value.copy(isLoading = true)
        taskUseCases.getTasks()
            .onEach { tasks ->
                _state.value = _state.value.copy(
                    tasks = tasks,
                    isLoading = false,
                    error = null
                )
            }
            .catch { e ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unexpected error occurred"
                )
            }
            .launchIn(viewModelScope)
    }

    fun addTask(task: Task) {

        viewModelScope.launch {

            try {

                taskUseCases.addTask(task)

            } catch (e: Exception) {

                _state.value = _state.value.copy(
                    error = e.message
                )

            }

        }

    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskUseCases.updateTask(task)
        }
    }

    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            taskUseCases.updateTask(task.copy(isCompleted = !task.isCompleted))
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskUseCases.deleteTask(task)
        }
    }

    fun syncFromCloud() {
        viewModelScope.launch {
            try {
                taskUseCases.syncFromCloud()
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message
                )
            }
        }
    }

    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }
}