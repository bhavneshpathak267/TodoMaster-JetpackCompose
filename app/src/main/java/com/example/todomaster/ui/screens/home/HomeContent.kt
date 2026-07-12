package com.example.todomaster.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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

    val completed = tasks.count { it.isCompleted }
    val pending = tasks.count { !it.isCompleted }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        SearchTextField(
            value = searchQuery,
            onValueChange = {
                onEvent(HomeEvent.SearchQueryChanged(it))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            DashboardCard(
                title = "Total",
                value = tasks.size.toString(),
                modifier = Modifier.weight(1f)
            )

            DashboardCard(
                title = "Pending",
                value = pending.toString(),
                modifier = Modifier.weight(1f)
            )

            DashboardCard(
                title = "Done",
                value = completed.toString(),
                modifier = Modifier.weight(1f)
            )

        }

        Spacer(modifier = Modifier.height(18.dp))

        when {

            isLoading -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressIndicator()

                }

            }

            tasks.isEmpty() -> {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "📋",
                            fontSize = 60.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            "No Tasks Yet",
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Text(
                            "Tap + button to create your first task"
                        )

                    }

                }

            }

            else -> {

                LazyColumn(
                    contentPadding = PaddingValues(bottom = 120.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(
                        tasks,
                        key = { it.id }
                    ) { task ->

                        TaskCard(
                            task = task,

                            onCheckedChange = {
                                onEvent(HomeEvent.ToggleTask(task))
                            },

                            onEditClick = {
                                onTaskClick(task.id)
                            },

                            onDeleteClick = {
                                onEvent(HomeEvent.DeleteTask(task))
                            },

                            onTaskClick = {
                                onTaskClick(task.id)
                            }
                        )

                    }

                }

            }

        }

    }

}

@Composable
private fun DashboardCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                title,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

        }

    }

}