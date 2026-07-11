package com.example.todomaster.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todomaster.data.model.User
import com.example.todomaster.data.remote.FirestoreProvider
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val db = FirestoreProvider.db

    // Register
    fun registerUser(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->

                val firebaseUser = result.user

                if (firebaseUser != null) {

                    firebaseUser.sendEmailVerification()

                    val user = User(
                        uid = firebaseUser.uid,
                        name = name,
                        email = email
                    )

                    db.collection("users")
                        .document(firebaseUser.uid)
                        .set(user)
                        .addOnSuccessListener {

                            onSuccess()

                        }
                        .addOnFailureListener {

                            onFailure(it.message ?: "Firestore Error")

                        }

                }

            }
            .addOnFailureListener {

                onFailure(it.message ?: "Unknown Error")

            }

    }

    // Login
    fun loginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {

                auth.currentUser?.reload()?.addOnSuccessListener {

                    if (auth.currentUser?.isEmailVerified == true) {

                        onSuccess()

                    } else {

                        auth.signOut()

                        onFailure("Please verify your email first.")

                    }

                }

            }
            .addOnFailureListener {

                onFailure(it.message ?: "Unknown Error")

            }

    }

    // Auto Login
    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    // Current User Email
    fun getCurrentUserEmail(): String {
        return auth.currentUser?.email ?: "No User"
    }

    // Logout
    fun logout() {
        auth.signOut()
    }

    // Forgot Password
    fun sendPasswordResetEmail(
        email: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.message ?: "Unknown Error")
            }

    }

}