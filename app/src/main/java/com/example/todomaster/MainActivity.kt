package com.example.todomaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.todomaster.navigation.TodoNavGraph
import com.example.todomaster.ui.theme.TodoMasterTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TodoMasterTheme {
                TodoNavGraph()
            }
        }
    }
}