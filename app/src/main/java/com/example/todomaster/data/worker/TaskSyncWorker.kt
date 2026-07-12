package com.example.todomaster.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.todomaster.data.local.DatabaseProvider
import com.example.todomaster.data.mapper.toTask
import com.example.todomaster.data.remote.TaskRemoteDataSource

class TaskSyncWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

        return try {

            val database = DatabaseProvider.provideDatabase(applicationContext)

            val dao = database.taskDao

            val remote = TaskRemoteDataSource()

            val tasks = dao.getAllTasksOnce()

            tasks.forEach { entity ->

                remote.uploadTask(
                    entity.toTask()
                )

            }

            Result.success()

        } catch (e: Exception) {

            e.printStackTrace()

            Result.retry()

        }

    }
}