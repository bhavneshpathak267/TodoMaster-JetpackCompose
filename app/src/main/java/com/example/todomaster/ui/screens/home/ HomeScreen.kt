package com.example.todomaster.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.todomaster.domain.model.Task

@Composable
fun HomeScreen(
    navController: NavController
) {

    val sampleTasks = listOf(

        Task(
            id = 1,
            title = "Learn Kotlin",
            description = "Complete Compose Basics",
            isCompleted = false
        ),

        Task(
            id = 2,
            title = "TodoMaster UI",
            description = "Design Home Screen",
            isCompleted = true
        ),

        Task(
            id = 3,
            title = "Push to GitHub",
            description = "Commit Phase 4",
            isCompleted = false
        )

    )

    HomeContent(
        navController = navController,
        tasks = sampleTasks,
        searchText = "",
        onSearchChange = {}
    )
}