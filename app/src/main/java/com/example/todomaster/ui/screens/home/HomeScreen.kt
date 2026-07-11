package com.example.todomaster.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.todomaster.ui.components.topbar.HomeTopBar
import com.example.todomaster.viewmodel.TaskViewModel

@Composable
fun HomeScreen(
    viewModel: TaskViewModel,
    onNavigateToAddTask: () -> Unit,
    onNavigateToEditTask: (Int) -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToProfile: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    Scaffold(

        topBar = {

            HomeTopBar(

                onProfileClick = onNavigateToProfile,

                onSettingsClick = onNavigateToSettings

            )

        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = onNavigateToAddTask
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Task"
                )

            }

        }

    ) { padding ->

        HomeContent(

            tasks = state.tasks,

            isLoading = state.isLoading,

            searchQuery = "",

            onEvent = { event ->

                when (event) {

                    is HomeEvent.ToggleTask ->
                        viewModel.toggleTaskCompletion(event.task)

                    is HomeEvent.DeleteTask ->
                        viewModel.deleteTask(event.task)

                    is HomeEvent.SearchQueryChanged -> {
                        // Search will be implemented later
                    }

                }

            },

            onTaskClick = onNavigateToEditTask,

            modifier = Modifier.padding(padding)

        )

    }

}