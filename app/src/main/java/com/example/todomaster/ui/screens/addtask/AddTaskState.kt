package com.example.todomaster.ui.screens.addtask

import com.example.todomaster.domain.model.Category
import com.example.todomaster.domain.model.Priority
import java.util.Date

data class AddTaskState(

    val title: String = "",

    val description: String = "",

    val priority: Priority = Priority.MEDIUM,

    val category: Category = Category.PERSONAL,

    val dueDate: Date? = null,

    val reminderTime: Date? = null,

    val color: Long = 0xFF6750A4,

    val isSaving: Boolean = false

) {

    val canSave: Boolean
        get() = title.isNotBlank()

}