package com.example.todomaster.ui.screens.addtask

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddTaskContent(
    state: AddTaskState,
    onEvent: (AddTaskEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = state.title,
            onValueChange = { onEvent(AddTaskEvent.TitleChanged(it)) },
            label = { Text("Task Title") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.description,
            onValueChange = { onEvent(AddTaskEvent.DescriptionChanged(it)) },
            label = { Text("Description (Optional)") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Button(
            onClick = { onEvent(AddTaskEvent.SaveTask) },
            enabled = state.canSave,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (state.isSaving) "Saving..." else "Save Task")
        }
    }
}