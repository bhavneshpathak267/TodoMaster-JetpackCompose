package com.example.todomaster.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todomaster.domain.model.Task
import com.example.todomaster.ui.components.cards.TaskCard
import com.example.todomaster.ui.components.textfield.SearchTextField

@Composable
fun HomeContent(
    tasks: List<Task>,
    isLoading: Boolean,
    searchQuery: String,
    onEvent: (HomeEvent) -> Unit,
    onTaskClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        SearchTextField(
            value = searchQuery,
            onValueChange = { onEvent(HomeEvent.SearchQueryChanged(it)) },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator()
                }
                tasks.isEmpty() -> {
                    Text(
                        text = if (searchQuery.isBlank()) "No tasks yet! Tap + to create one." else "No matching tasks found.",
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 80.dp)
                    ) {
                        items(tasks, key = { it.id }) { task ->
                            TaskCard(
                                task = task,
                                onCheckedChange = { onEvent(HomeEvent.ToggleTask(task)) },
                                onTaskClick = { onTaskClick(task.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}