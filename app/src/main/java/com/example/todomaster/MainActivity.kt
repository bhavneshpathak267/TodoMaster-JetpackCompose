package com.example.todomaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.todomaster.data.worker.SyncScheduler
import com.example.todomaster.di.AppContainer
import com.example.todomaster.navigation.NavGraph
import com.example.todomaster.ui.theme.TodoMasterTheme
import com.example.todomaster.viewmodel.AuthViewModel
import com.example.todomaster.viewmodel.TaskViewModel
import com.example.todomaster.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Schedule background task sync
        SyncScheduler.schedule(applicationContext)

        // Dependency Injection
        val appContainer = AppContainer(applicationContext)

        // ViewModel Factory
        val viewModelFactory =
            TaskViewModelFactory(appContainer.taskUseCases)

        val viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            )[TaskViewModel::class.java]

        val authViewModel =
            ViewModelProvider(this)[AuthViewModel::class.java]

        setContent {

            TodoMasterTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavGraph(
                        navController = navController,
                        viewModel = viewModel,
                        authViewModel = authViewModel
                    )

                }

            }

        }

    }

}