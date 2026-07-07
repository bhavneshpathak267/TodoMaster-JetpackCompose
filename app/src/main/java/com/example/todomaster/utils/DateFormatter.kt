package com.example.todomaster.ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateFormatter {
    private val defaultDateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    fun formatDate(date: Date?): String {
        return date?.let { defaultDateFormatter.format(it) } ?: "No due date"
    }

    fun formatDateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}