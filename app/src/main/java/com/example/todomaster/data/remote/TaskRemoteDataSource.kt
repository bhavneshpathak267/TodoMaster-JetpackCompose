package com.example.todomaster.data.remote

import com.example.todomaster.data.mapper.toFirestoreTask
import com.example.todomaster.domain.model.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TaskRemoteDataSource(

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

) {

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

        db.collection("tasks")
            .document(uid)
            .collection("userTasks")
            .document(task.id.toString())
            .set(task.toFirestoreTask(uid))
            .addOnSuccessListener {

                onSuccess()

            }
            .addOnFailureListener {

                onFailure(it.message ?: "Upload Failed")

            }

    }

}