package com.example.todomaster.ui.components.category

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.todomaster.domain.model.TaskCategory

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdown(
    selected: TaskCategory,
    onSelected: (TaskCategory) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {

        OutlinedTextField(

            value = selected.name,

            onValueChange = {},

            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),

            readOnly = true,

            label = {
                Text("Category")
            },

            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            },

            textStyle = MaterialTheme.typography.bodyLarge

        )

        ExposedDropdownMenu(

            expanded = expanded,

            onDismissRequest = {

                expanded = false

            }

        ) {

            TaskCategory.entries.forEach { category ->

                DropdownMenuItem(

                    text = {

                        Text(category.name)

                    },

                    onClick = {

                        onSelected(category)

                        expanded = false

                    }

                )

            }

        }

    }

}