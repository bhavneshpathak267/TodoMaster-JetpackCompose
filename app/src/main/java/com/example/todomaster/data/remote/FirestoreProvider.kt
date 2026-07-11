package com.example.todomaster.data.remote

import com.google.firebase.firestore.FirebaseFirestore

object FirestoreProvider {

    val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

}