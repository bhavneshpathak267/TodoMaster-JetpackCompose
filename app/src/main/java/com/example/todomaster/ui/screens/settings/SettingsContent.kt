package com.example.todomaster.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SettingsContent(
    modifier: Modifier = Modifier,
    userEmail: String,
    onLogoutClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Logged in as",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = userEmail,
                    style = MaterialTheme.typography.bodyMedium
                )

            }

        }

        HorizontalDivider()

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Icon(Icons.Default.DarkMode, null)

                Spacer(modifier = Modifier.height(8.dp))

                Text("Dark Mode")

                Text(
                    "Coming Soon",
                    style = MaterialTheme.typography.bodySmall
                )

            }

        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Icon(Icons.Default.Notifications, null)

                Spacer(modifier = Modifier.height(8.dp))

                Text("Notifications")

                Text(
                    "Coming Soon",
                    style = MaterialTheme.typography.bodySmall
                )

            }

        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Icon(Icons.Default.Info, null)

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "TodoMaster",
                    fontWeight = FontWeight.Bold
                )

                Text("Version 1.0")

                Text(
                    "Built using Jetpack Compose + Firebase"
                )

            }

        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onLogoutClick
        ) {

            Icon(
                Icons.Default.ExitToApp,
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text("Logout")

        }

    }

}