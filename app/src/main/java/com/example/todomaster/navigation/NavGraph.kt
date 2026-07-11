package com.example.todomaster.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todomaster.ui.screens.addtask.AddTaskScreen
import com.example.todomaster.ui.screens.edittask.EditTaskScreen
import com.example.todomaster.ui.screens.forgotpassword.ForgotPasswordScreen
import com.example.todomaster.ui.screens.home.HomeScreen
import com.example.todomaster.ui.screens.login.LoginScreen
import com.example.todomaster.ui.screens.profile.ProfileScreen
import com.example.todomaster.ui.screens.register.RegisterScreen
import com.example.todomaster.ui.screens.settings.SettingsScreen
import com.example.todomaster.ui.screens.splash.SplashScreen
import com.example.todomaster.viewmodel.AuthViewModel
import com.example.todomaster.viewmodel.TaskViewModel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: TaskViewModel,
    authViewModel: AuthViewModel
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        // Splash
        composable(Screen.Splash.route) {
            SplashScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        // Login
        composable(Screen.Login.route) {
            LoginScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        // Register
        composable(Screen.Register.route) {
            RegisterScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        // Forgot Password
        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        // Home
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,

                onNavigateToAddTask = {
                    navController.navigate(Screen.AddTask.route)
                },

                onNavigateToEditTask = { taskId ->
                    navController.navigate(
                        Screen.EditTask.passTaskId(taskId)
                    )
                },

                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                },

                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

        // Add Task
        composable(Screen.AddTask.route) {
            AddTaskScreen(
                viewModel = viewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // Edit Task
        composable(
            route = Screen.EditTask.route,
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val taskId =
                backStackEntry.arguments?.getInt("taskId") ?: -1

            EditTaskScreen(
                taskId = taskId,
                viewModel = viewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // Settings
        composable(Screen.Settings.route) {
            SettingsScreen(
                navController = navController,
                authViewModel = authViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // Profile
        composable(Screen.Profile.route) {
            ProfileScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}