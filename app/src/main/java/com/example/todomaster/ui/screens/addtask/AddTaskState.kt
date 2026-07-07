package com.example.todomaster.ui.screens.addtask

data class AddTaskState(
    val title: String = "",
    val description: String = "",
    val isSaving: Boolean = false,
    val error: String? = null
) {
    val canSave: Boolean get() = title.isNotBlank() && !isSaving
}