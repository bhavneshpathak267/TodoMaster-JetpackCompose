package com.example.todomaster.ui.screens.home

sealed interface HomeEvent {

    data object AddTask : HomeEvent

    data class DeleteTask(
        val taskId: Int
    ) : HomeEvent

    data class EditTask(
        val taskId: Int
    ) : HomeEvent

    data class ToggleTask(
        val taskId: Int
    ) : HomeEvent

    data object OpenSettings : HomeEvent
}