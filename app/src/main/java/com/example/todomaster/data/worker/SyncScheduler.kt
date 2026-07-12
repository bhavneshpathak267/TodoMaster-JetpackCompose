package com.example.todomaster.data.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object SyncScheduler {

    private const val WORK_NAME = "TASK_SYNC_WORK"

    fun schedule(context: Context) {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request =

            PeriodicWorkRequestBuilder<TaskSyncWorker>(
                15,
                TimeUnit.MINUTES
            )

                .setConstraints(constraints)

                .build()

        WorkManager.getInstance(context)

            .enqueueUniquePeriodicWork(

                WORK_NAME,

                ExistingPeriodicWorkPolicy.KEEP,

                request

            )

    }

}