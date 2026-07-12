package com.example.todomaster.ui.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todomaster.domain.model.Task
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TaskCard(
    task: Task,
    onCheckedChange: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onTaskClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onTaskClick()
            },
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(18.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = {
                        onCheckedChange()
                    }
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = task.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textDecoration =
                            if (task.isCompleted)
                                TextDecoration.LineThrough
                            else
                                TextDecoration.None
                    )

                    if (task.description.isNotBlank()) {

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = task.description,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                    }

                }

            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(50)
                ) {

                    Text(
                        text = "Task",
                        modifier = Modifier.padding(
                            horizontal = 12.dp,
                            vertical = 5.dp
                        ),
                        color = MaterialTheme.colorScheme.primary
                    )

                }

                Spacer(modifier = Modifier.width(12.dp))

                if (task.dueDate != null) {

                    Icon(
                        Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        formatter.format(task.dueDate),
                        fontSize = 13.sp
                    )

                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = onEditClick
                ) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Edit"
                    )
                }

                IconButton(
                    onClick = onDeleteClick
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error
                    )
                }

            }

        }

    }

}