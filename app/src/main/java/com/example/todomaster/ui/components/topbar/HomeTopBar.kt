package com.example.todomaster.ui.components.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(

    onProfileClick: () -> Unit,

    onSettingsClick: () -> Unit

) {

    TopAppBar(

        title = {

            Text("TodoMaster")

        },

        actions = {

            IconButton(
                onClick = onProfileClick
            ) {

                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile"
                )

            }

            IconButton(
                onClick = onSettingsClick
            ) {

                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )

            }

        }

    )

}