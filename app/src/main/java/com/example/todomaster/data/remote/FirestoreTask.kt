package com.example.todomaster.data.remote

data class FirestoreTask(

    val id: Int = 0,

    val cloudId: String = "",

    val title: String = "",

    val description: String = "",

    val isCompleted: Boolean = false,

    val createdAt: Long = 0L,

    val dueDate: Long? = null,

    val userId: String = ""

)