package com.example.todomaster.data.remote

import com.example.todomaster.domain.model.Task

class TaskSyncManager(

    private val remoteDataSource: TaskRemoteDataSource

) {

    suspend fun syncTask(task: Task): Task {

        return remoteDataSource.uploadTask(task)

    }

}