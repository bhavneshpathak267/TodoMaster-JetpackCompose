package com.example.todomaster.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.todomaster.data.model.User
import com.example.todomaster.data.remote.FirestoreProvider
import com.example.todomaster.data.state.UserState
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val db = FirestoreProvider.db

    var userState by mutableStateOf(UserState())
        private set

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
                        email = email,
                        bio = "",
                        photoUri = ""
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

                        getUserData()

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

    // Fetch User Data From Firestore
    fun getUserData() {

        val uid = auth.currentUser?.uid ?: return

        db.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->

                if (document.exists()) {

                    userState = UserState(

                        uid = document.getString("uid") ?: "",

                        name = document.getString("name") ?: "",

                        email = document.getString("email") ?: "",

                        bio = document.getString("bio") ?: "",

                        photoUri = document.getString("photoUri") ?: ""

                    )

                }

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

    // Update User Name
    fun updateUserName(
        newName: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        val uid = auth.currentUser?.uid

        if (uid == null) {
            onFailure("User not logged in")
            return
        }

        db.collection("users")
            .document(uid)
            .update("name", newName)
            .addOnSuccessListener {

                userState = userState.copy(
                    name = newName
                )

                onSuccess()

            }
            .addOnFailureListener {

                onFailure(it.message ?: "Update Failed")

            }

    }

    fun updateUserBio(
        newBio: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        val uid = auth.currentUser?.uid ?: return

        db.collection("users")
            .document(uid)
            .update("bio", newBio)
            .addOnSuccessListener {

                userState = userState.copy(
                    bio = newBio
                )

                onSuccess()

            }
            .addOnFailureListener {

                onFailure(it.message ?: "Unknown Error")

            }

    }

}