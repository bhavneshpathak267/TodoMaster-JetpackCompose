package com.example.todomaster.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.todomaster.utils.NotificationHelper

class ReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val taskId = inputData.getInt("taskId", -1)
        val title = inputData.getString("title") ?: "Task Reminder"
        val description = inputData.getString("description") ?: ""

        if (taskId != -1) {
            NotificationHelper(applicationContext).showNotification(
                taskId = taskId,
                title = title,
                message = description
            )
        }

        return Result.success()
    }
}
