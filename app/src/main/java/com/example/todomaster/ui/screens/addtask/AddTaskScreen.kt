package com.example.todomaster.ui.screens.addtask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todomaster.ui.components.category.CategoryDropdown
import com.example.todomaster.ui.components.date.DatePickerField
import com.example.todomaster.ui.components.priority.PrioritySelector
import com.example.todomaster.ui.components.time.TimePickerField
import com.example.todomaster.viewmodel.TaskViewModel
import com.example.todomaster.domain.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    viewModel: TaskViewModel,
    onNavigateBack: () -> Unit
) {

    var state by remember {
        mutableStateOf(AddTaskState())
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        "Add New Task",
                        fontWeight = FontWeight.Bold
                    )

                },

                navigationIcon = {

                    IconButton(
                        onClick = onNavigateBack
                    ) {

                        Icon(
                            Icons.Default.ArrowBack,
                            null
                        )

                    }

                }

            )

        }

    ) { padding ->

        Column(

            modifier = Modifier

                .padding(padding)

                .fillMaxSize()

                .background(

                    Brush.verticalGradient(

                        listOf(

                            Color(0xFFEDE7F6),

                            Color.White

                        )

                    )

                )

                .verticalScroll(
                    rememberScrollState()
                )

                .padding(20.dp)

        ) {

            Card(

                modifier = Modifier.fillMaxWidth(),

                colors = CardDefaults.cardColors(

                    containerColor = Color.White

                ),

                elevation = CardDefaults.cardElevation(
                    8.dp
                ),

                shape = RoundedCornerShape(24.dp)

            ) {

                Column(

                    modifier = Modifier.padding(20.dp)

                ) {

                    Row(

                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Icon(

                            Icons.Default.Task,

                            contentDescription = null,

                            tint = Color(0xFF6750A4)

                        )

                        Spacer(
                            Modifier.width(12.dp)
                        )

                        Text(

                            "Task Details",

                            fontWeight = FontWeight.Bold,

                            fontSize = 22.sp

                        )

                    }

                    Spacer(
                        Modifier.height(20.dp)
                    )

                    OutlinedTextField(

                        value = state.title,

                        onValueChange = {

                            state = state.copy(

                                title = it

                            )

                        },

                        modifier = Modifier.fillMaxWidth(),

                        label = {

                            Text("Title")

                        },

                        singleLine = true

                    )

                    Spacer(
                        Modifier.height(16.dp)
                    )

                    OutlinedTextField(

                        value = state.description,

                        onValueChange = {

                            state = state.copy(

                                description = it

                            )

                        },

                        modifier = Modifier.fillMaxWidth(),

                        minLines = 4,

                        label = {

                            Text("Description")

                        }

                    )

                    Spacer(
                        Modifier.height(20.dp)
                    )

                    Text(

                        "Priority",

                        fontWeight = FontWeight.SemiBold

                    )

                    Spacer(
                        Modifier.height(8.dp)
                    )

                    PrioritySelector(

                        selected = state.priority,

                        onSelected = {

                            state = state.copy(

                                priority = it

                            )

                        }

                    )

                    Spacer(
                        Modifier.height(20.dp)
                    )

                    CategoryDropdown(

                        selected = state.category,

                        onSelected = {

                            state = state.copy(

                                category = it

                            )

                        }

                    )

                    Spacer(
                        Modifier.height(20.dp)
                    )

                    DatePickerField(

                        selectedDate = state.dueDate,

                        onDateSelected = {

                            state = state.copy(

                                dueDate = it

                            )

                        }

                    )

                    Spacer(
                        Modifier.height(16.dp)
                    )

                    TimePickerField(

                        selectedTime = state.reminderTime,

                        onTimeSelected = {

                            state = state.copy(

                                reminderTime = it

                            )

                        }

                    )
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    OutlinedTextField(

                        value = state.notes,

                        onValueChange = {

                            state = state.copy(
                                notes = it
                            )

                        },

                        modifier = Modifier.fillMaxWidth(),

                        minLines = 4,

                        maxLines = 6,

                        label = {

                            Text("Notes")

                        }

                    )

                    Spacer(
                        modifier = Modifier.height(28.dp)
                    )

                    Button(

                        onClick = {

                            if (state.canSave) {

                                viewModel.addTask(
                                    Task(
                                        title = state.title,
                                        description = state.description,
                                        priority = state.priority,
                                        category = state.category,
                                        dueDate = state.dueDate,
                                        reminderTime = state.reminderTime,
                                        notes = state.notes,
                                        color = state.color
                                    )
                                )

                                onNavigateBack()

                            }

                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),

                        shape = RoundedCornerShape(18.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6750A4)
                        )

                    ) {

                        Text(

                            text = "💜 Save Task",

                            fontSize = 18.sp,

                            fontWeight = FontWeight.Bold

                        )

                    }

                }

            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )

        }

    }

}