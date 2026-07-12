package com.example.todomaster.ui.components.time

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun TimePickerField(
    selectedTime: Date?,
    onTimeSelected: (Date?) -> Unit
) {

    val context = LocalContext.current

    val calendar = Calendar.getInstance()

    selectedTime?.let {
        calendar.time = it
    }

    val formatter = SimpleDateFormat(
        "hh:mm a",
        Locale.getDefault()
    )

    val dialog = TimePickerDialog(
        context,
        { _, hour, minute ->

            val pickedCalendar = Calendar.getInstance()

            pickedCalendar.time = selectedTime ?: Date()

            pickedCalendar.set(Calendar.HOUR_OF_DAY, hour)
            pickedCalendar.set(Calendar.MINUTE, minute)
            pickedCalendar.set(Calendar.SECOND, 0)

            onTimeSelected(pickedCalendar.time)

        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        false
    )

    OutlinedTextField(
        value = selectedTime?.let {
            formatter.format(it)
        } ?: "",

        onValueChange = {},

        readOnly = true,

        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                dialog.show()
            },

        label = {
            Text("Reminder Time")
        },

        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Schedule,
                contentDescription = null
            )
        }
    )
}