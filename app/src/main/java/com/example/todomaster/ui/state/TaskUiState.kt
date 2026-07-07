package com.example.todomaster.ui.state

import com.example.todomaster.domain.model.Task

data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)