package com.example.todomaster.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todomaster.domain.model.Task
import com.example.todomaster.ui.components.buttons.AddTaskFab
import com.example.todomaster.ui.components.cards.TaskCard
import com.example.todomaster.ui.components.textfield.SearchTextField
import com.example.todomaster.ui.components.topbar.HomeTopBar
import com.example.todomaster.navigation.Screen

@Composable
fun HomeContent(
    navController: NavController,
    tasks: List<Task>,
    searchText: String,
    onSearchChange: (String) -> Unit
) {

    Scaffold(

        topBar = {
            HomeTopBar(
                onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        },

        floatingActionButton = {
            AddTaskFab {
                navController.navigate(Screen.AddTask.route)
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            SearchTextField(
                value = searchText,
                onValueChange = onSearchChange
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(tasks) { task ->

                    TaskCard(
                        task = task,
                        onCheckedChange = {},
                        onMenuClick = {}
                    )
                }
            }
        }
    }
}