package com.example.todomaster.ui.screens.home

import com.example.todomaster.domain.model.Task

sealed interface HomeEvent {
    data class SearchQueryChanged(val query: String) : HomeEvent
    data class ToggleTask(val task: Task) : HomeEvent
    data class DeleteTask(val task: Task) : HomeEvent
}