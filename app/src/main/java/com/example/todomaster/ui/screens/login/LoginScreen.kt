package com.example.todomaster.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todomaster.navigation.Screen
import com.example.todomaster.viewmodel.AuthViewModel
import com.example.todomaster.viewmodel.TaskViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    taskViewModel: TaskViewModel
) {

    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.headlineLarge
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Email")
            }
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Password")
            }
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                authViewModel.loginUser(

                    email = email,
                    password = password,

                    onSuccess = {

                        taskViewModel.syncFromCloud()

                        Toast.makeText(
                            context,
                            "Login Successful",
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.navigate(Screen.Home.route)

                    },

                    onFailure = {

                        Toast.makeText(
                            context,
                            it,
                            Toast.LENGTH_LONG
                        ).show()

                    }

                )

            }
        ) {

            Text("Login")

        }

        TextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = {
                navController.navigate(Screen.ForgotPassword.route)
            }
        ) {
            Text("Forgot Password?")
        }

        TextButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {

                navController.navigate(Screen.Register.route)

            }
        ) {

            Text("Create New Account")

        }

    }

}