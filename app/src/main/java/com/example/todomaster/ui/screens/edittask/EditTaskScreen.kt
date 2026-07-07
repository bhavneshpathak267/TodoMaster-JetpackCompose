package com.example.todomaster.ui.screens.edittask

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.todomaster.ui.components.dialogs.DeleteTaskDialog
import com.example.todomaster.ui.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    taskId: Int,
    viewModel: TaskViewModel,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.state.collectAsState()

    // Locate the matching task entity from state memory
    val task = remember(taskId, uiState.tasks) {
        uiState.tasks.find { it.id == taskId }
    }

    if (task == null) {
        LaunchedEffect(Unit) { onNavigateBack() }
        return
    }

    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Task") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        EditTaskContent(
            title = title,
            onTitleChange = { title = it },
            description = description,
            onDescriptionChange = { description = it },
            onSaveClick = {
                viewModel.updateTask(task.copy(title = title, description = description))
                onNavigateBack()
            },
            onDeleteClick = { showDeleteDialog = true },
            modifier = Modifier.padding(paddingValues)
        )

        if (showDeleteDialog) {
            DeleteTaskDialog(
                onConfirm = {
                    showDeleteDialog = false
                    viewModel.deleteTask(task)
                    onNavigateBack()
                },
                onDismiss = { showDeleteDialog = false }
            )
        }
    }
}