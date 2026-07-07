package com.example.todomaster.ui.screens.addtask

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.todomaster.ui.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    viewModel: TaskViewModel,
    onNavigateBack: () -> Unit
) {
    var state by remember { mutableStateOf(AddTaskState()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add New Task") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        AddTaskContent(
            state = state,
            onEvent = { event ->
                when (event) {
                    is AddTaskEvent.TitleChanged -> {
                        state = state.copy(title = event.title)
                    }
                    is AddTaskEvent.DescriptionChanged -> {
                        state = state.copy(description = event.description)
                    }
                    is AddTaskEvent.SaveTask -> {
                        if (state.canSave) {
                            state = state.copy(isSaving = true)
                            viewModel.addTask(state.title, state.description)
                            onNavigateBack()
                        }
                    }
                }
            },
            modifier = Modifier.padding(paddingValues)
        )
    }
}