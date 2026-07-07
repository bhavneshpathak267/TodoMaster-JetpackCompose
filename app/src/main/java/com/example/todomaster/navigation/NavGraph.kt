package com.example.todomaster.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todomaster.ui.screens.addtask.AddTaskScreen
import com.example.todomaster.ui.screens.edittask.EditTaskScreen
import com.example.todomaster.ui.screens.home.HomeScreen
import com.example.todomaster.ui.screens.settings.SettingsScreen

@Composable
fun TodoNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.AddTask.route) {
            AddTaskScreen(navController)
        }

        composable(Screen.EditTask.route) {
            EditTaskScreen(navController)
        }

        composable(Screen.Settings.route) {
            SettingsScreen(navController)
        }

    }
}