package com.example.todomaster.ui.screens.addtask

import com.example.todomaster.domain.model.Category
import com.example.todomaster.domain.model.Priority
import java.util.Date

sealed interface AddTaskEvent {

    data class TitleChanged(
        val title: String
    ) : AddTaskEvent

    data class DescriptionChanged(
        val description: String
    ) : AddTaskEvent

    data class PriorityChanged(
        val priority: Priority
    ) : AddTaskEvent

    data class CategoryChanged(
        val category: Category
    ) : AddTaskEvent

    data class DueDateChanged(
        val date: Date
    ) : AddTaskEvent

    data class ReminderChanged(
        val date: Date
    ) : AddTaskEvent

    data class ColorChanged(
        val color: Long
    ) : AddTaskEvent

    object SaveTask : AddTaskEvent

}