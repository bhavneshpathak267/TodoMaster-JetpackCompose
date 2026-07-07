package com.example.todomaster.navigation

sealed class Screen(val route: String) {

    data object Home : Screen("home")

    data object AddTask : Screen("add_task")

    data object EditTask : Screen("edit_task")

    data object Settings : Screen("settings")
}