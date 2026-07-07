package com.example.todomaster.ui.screens.home

import com.example.todomaster.domain.model.Task

data class HomeUiState(

    val searchText: String = "",

    val tasks: List<Task> = emptyList()

)