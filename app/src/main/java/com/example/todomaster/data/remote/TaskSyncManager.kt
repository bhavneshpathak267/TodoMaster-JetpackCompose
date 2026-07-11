package com.example.todomaster.data.remote

import com.example.todomaster.domain.model.Task

class TaskSyncManager(

    private val remoteDataSource: TaskRemoteDataSource

) {

    fun syncTask(

        task: Task,

        onSuccess: (Task) -> Unit,

        onFailure: (String) -> Unit

    ) {

        remoteDataSource.uploadTask(

            task = task,

            onSuccess = onSuccess,

            onFailure = onFailure

        )

    }

}