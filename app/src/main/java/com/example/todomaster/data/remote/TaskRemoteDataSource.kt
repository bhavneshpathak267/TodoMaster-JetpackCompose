package com.example.todomaster.data.remote

import com.example.todomaster.data.mapper.toFirestoreTask
import com.example.todomaster.domain.model.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TaskRemoteDataSource(

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

) {

    private fun tasksCollection() =
        db.collection("users")
            .document(auth.currentUser?.uid ?: "")
            .collection("tasks")

    fun uploadTask(
        task: Task,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        val uid = auth.currentUser?.uid

        if (uid == null) {
            onFailure("User not logged in")
            return
        }

        val document =
            if (task.cloudId.isBlank()) {
                tasksCollection().document()
            } else {
                tasksCollection().document(task.cloudId)
            }

        val firestoreTask =
            task.copy(cloudId = document.id)
                .toFirestoreTask(uid)

        document
            .set(firestoreTask)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.message ?: "Upload failed")
            }

    }

}