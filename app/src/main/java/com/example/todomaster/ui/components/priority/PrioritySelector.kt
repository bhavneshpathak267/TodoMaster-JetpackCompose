package com.example.todomaster.ui.components.priority

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todomaster.domain.model.Priority

@Composable
fun PrioritySelector(
    selected: Priority,
    onSelected: (Priority) -> Unit
) {

    FlowRow(

        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {

        Priority.entries.forEach { priority ->

            FilterChip(

                selected = selected == priority,

                onClick = {

                    onSelected(priority)

                },

                label = {

                    Text(priority.name)

                }

            )

        }

    }

}