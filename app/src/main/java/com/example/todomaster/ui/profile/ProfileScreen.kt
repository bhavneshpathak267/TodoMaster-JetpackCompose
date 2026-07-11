package com.example.todomaster.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todomaster.navigation.Screen
import com.example.todomaster.viewmodel.AuthViewModel
import androidx.compose.runtime.Composable

@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {

    LaunchedEffect(Unit) {
        authViewModel.getUserData()
    }

    val user = authViewModel.userState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "My Profile",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Name",
            style = MaterialTheme.typography.titleMedium
        )

        Text(user.name)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Email",
            style = MaterialTheme.typography.titleMedium
        )

        Text(user.email)

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {

                authViewModel.logout()

                navController.navigate(Screen.Login.route) {

                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }

                }

            }
        ) {

            Text("Logout")

        }

    }

}