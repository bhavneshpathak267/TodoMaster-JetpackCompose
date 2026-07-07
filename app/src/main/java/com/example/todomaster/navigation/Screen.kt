package com.example.todomaster.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object AddTask : Screen("add_task_screen")
    object EditTask : Screen("edit_task_screen/{taskId}") {
        fun passTaskId(taskId: Int): String {
            return "edit_task_screen/$taskId"
        }
    }
    object Settings : Screen("settings_screen")
}