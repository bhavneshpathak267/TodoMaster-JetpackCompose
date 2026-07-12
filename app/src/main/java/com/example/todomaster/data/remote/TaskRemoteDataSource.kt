package com.example.todomaster.data.remote

import com.example.todomaster.data.mapper.toFirestoreTask
import com.example.todomaster.data.mapper.toTask
import com.example.todomaster.domain.model.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.todomaster.data.model.FirestoreTask


class TaskRemoteDataSource(

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

) {

    private fun uid(): String =
        auth.currentUser?.uid
            ?: throw Exception("User not logged in")

    private fun taskCollection() =
        db.collection("users")
            .document(uid())
            .collection("tasks")

    suspend fun uploadTask(task: Task): Task {

        val document =
            if (task.cloudId.isBlank())
                taskCollection().document()
            else
                taskCollection().document(task.cloudId)

        val updatedTask =
            task.copy(
                cloudId = document.id
            )

        document
            .set(updatedTask.toFirestoreTask(uid()))
            .await()

        return updatedTask
    }

    suspend fun fetchTasks(): List<Task> {

        val snapshot =
            taskCollection()
                .get()
                .await()

        return snapshot.documents.mapNotNull {

            it.toObject(FirestoreTask::class.java)
                ?.toTask()

        }

    }

    suspend fun updateTask(task: Task) {

        if (task.cloudId.isBlank())
            return

        taskCollection()

            .document(task.cloudId)

            .set(task.toFirestoreTask(uid()))

            .await()

    }

    suspend fun deleteTask(cloudId: String) {

        if (cloudId.isBlank())
            return

        taskCollection()

            .document(cloudId)

            .delete()

            .await()

    }

    suspend fun downloadTasks(): List<Task> {

        val uid = FirebaseAuth.getInstance().currentUser?.uid
            ?: return emptyList()

        val snapshot = FirestoreProvider.db
            .collection("users")
            .document(uid)
            .collection("tasks")
            .get()
            .await()

        return snapshot.documents.mapNotNull {

            it.toObject(FirestoreTask::class.java)?.toTask()

        }
    }

}