package com.example.todomaster.data.remote

import com.example.todomaster.data.mapper.toFirestoreTask
import com.example.todomaster.data.mapper.toTask
import com.example.todomaster.domain.model.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TaskRemoteDataSource(

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

) {

    private fun currentUid(): String? = auth.currentUser?.uid

    private fun taskCollection() =
        db.collection("users")
            .document(currentUid() ?: "")
            .collection("tasks")

    fun uploadTask(
        task: Task,
        onSuccess: (Task) -> Unit,
        onFailure: (String) -> Unit
    ) {

        val uid = currentUid()

        if (uid == null) {
            onFailure("User not logged in")
            return
        }

        val document =
            if (task.cloudId.isBlank())
                taskCollection().document()
            else
                taskCollection().document(task.cloudId)

        val updatedTask = task.copy(
            cloudId = document.id
        )

        document
            .set(updatedTask.toFirestoreTask(uid))
            .addOnSuccessListener {
                onSuccess(updatedTask)
            }
            .addOnFailureListener {
                onFailure(it.message ?: "Upload failed")
            }

    }

    fun fetchTasks(

        onSuccess: (List<Task>) -> Unit,

        onFailure: (String) -> Unit

    ) {

        val uid = currentUid()

        if (uid == null) {

            onFailure("User not logged in")

            return

        }

        taskCollection()

            .get()

            .addOnSuccessListener { result ->

                val tasks = result.documents.mapNotNull {

                    it.toObject(FirestoreTask::class.java)?.toTask()

                }

                onSuccess(tasks)

            }

            .addOnFailureListener {

                onFailure(it.message ?: "Download failed")

            }

    }

    fun updateTask(

        task: Task,

        onSuccess: () -> Unit,

        onFailure: (String) -> Unit

    ) {

        val uid = currentUid()

        if (uid == null) {

            onFailure("User not logged in")

            return

        }

        if (task.cloudId.isBlank()) {

            onFailure("Missing cloudId")

            return

        }

        taskCollection()

            .document(task.cloudId)

            .set(task.toFirestoreTask(uid))

            .addOnSuccessListener {

                onSuccess()

            }

            .addOnFailureListener {

                onFailure(it.message ?: "Update failed")

            }

    }

    fun deleteTask(

        cloudId: String,

        onSuccess: () -> Unit,

        onFailure: (String) -> Unit

    ) {

        if (cloudId.isBlank()) {

            onFailure("Missing cloudId")

            return

        }

        taskCollection()

            .document(cloudId)

            .delete()

            .addOnSuccessListener {

                onSuccess()

            }

            .addOnFailureListener {

                onFailure(it.message ?: "Delete failed")

            }

    }

}