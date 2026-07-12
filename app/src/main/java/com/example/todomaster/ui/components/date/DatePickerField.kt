package com.example.todomaster.ui.components.date

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
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
fun DatePickerField(
    selectedDate: Date?,
    onDateSelected: (Date?) -> Unit
) {

    val context = LocalContext.current

    val calendar = Calendar.getInstance()

    selectedDate?.let {
        calendar.time = it
    }

    val formatter =
        SimpleDateFormat(
            "dd MMM yyyy",
            Locale.getDefault()
        )

    val dialog = DatePickerDialog(

        context,

        { _, year, month, day ->

            val pickedCalendar = Calendar.getInstance()

            pickedCalendar.set(
                year,
                month,
                day
            )

            onDateSelected(
                pickedCalendar.time
            )

        },

        calendar.get(Calendar.YEAR),

        calendar.get(Calendar.MONTH),

        calendar.get(Calendar.DAY_OF_MONTH)

    )

    OutlinedTextField(

        value = selectedDate?.let {

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

            Text("Due Date")

        },

        leadingIcon = {

            Icon(

                imageVector = Icons.Default.DateRange,

                contentDescription = null

            )

        }

    )

}