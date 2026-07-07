package com.example.todomaster.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            Text(text = "App Details", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "App Name: TodoMaster", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Version: 1.0.0", style = MaterialTheme.typography.bodyMedium)
        }

        HorizontalDivider()

        Column {
            Text(text = "About", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "A minimalist todo application designed with Clean Architecture principles and Jetpack Compose.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}