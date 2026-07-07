package com.example.todomaster.ui.screens.addtask

sealed interface AddTaskEvent {

    data class TitleChanged(
        val title: String
    ) : AddTaskEvent

    data class DescriptionChanged(
        val description: String
    ) : AddTaskEvent

    data object SaveTask : AddTaskEvent

    data object NavigateBack : AddTaskEvent
}