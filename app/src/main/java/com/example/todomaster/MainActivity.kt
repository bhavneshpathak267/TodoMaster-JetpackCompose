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
import com.example.todomaster.di.AppContainer
import com.example.todomaster.navigation.NavGraph
import com.example.todomaster.ui.theme.TodoMasterTheme
import com.example.todomaster.ui.viewmodel.TaskViewModel
import com.example.todomaster.ui.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize our manual DI container context
        val appContainer = AppContainer(applicationContext)

        // Pass use cases through our factory container architecture
        val viewModelFactory = TaskViewModelFactory(appContainer.taskUseCases)
        val viewModel = ViewModelProvider(this, viewModelFactory)[TaskViewModel::class.java]

        setContent {
            TodoMasterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavGraph(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}