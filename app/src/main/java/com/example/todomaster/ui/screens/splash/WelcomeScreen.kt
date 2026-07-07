package com.example.todomaster.ui.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.todomaster.navigation.Screen

@Composable
fun WelcomeScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Welcome to TodoMaster",
            style = MaterialTheme.typography.headlineMedium
        )

        Button(
            onClick = {
                navController.navigate(Screen.Login.route)
            }
        ) {

            Text("Get Started")

        }

    }

}