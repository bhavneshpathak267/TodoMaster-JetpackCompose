package com.example.todomaster.ui.screens.profile

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.todomaster.navigation.Screen
import com.example.todomaster.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        authViewModel.getUserData()
    }

    val user = authViewModel.userState

    var showNameDialog by remember { mutableStateOf(false) }
    var showBioDialog by remember { mutableStateOf(false) }

    var editedName by remember { mutableStateOf(user.name) }
    var editedBio by remember { mutableStateOf(user.bio) }

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val galleryLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->

            if (uri != null) {
                selectedImageUri = uri
            }

        }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("My Profile")
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )

                    }

                }

            )

        }

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {            Spacer(modifier = Modifier.height(20.dp))

            // Profile Image
            Box(
                contentAlignment = Alignment.BottomEnd
            ) {

                if (selectedImageUri != null) {

                    AsyncImage(
                        model = selectedImageUri,
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(140.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                } else {

                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.size(70.dp)
                        )

                    }

                }

                FloatingActionButton(
                    onClick = {

                        galleryLauncher.launch("image/*")

                    },
                    modifier = Modifier.size(45.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Change Photo"
                    )

                }

            }

            Spacer(modifier = Modifier.height(25.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Full Name",
                        style = MaterialTheme.typography.labelMedium
                    )

                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.labelMedium
                    )

                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.titleMedium
                    )

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            FloatingActionButton(
                onClick = {

                    editedName = user.name
                    showNameDialog = true

                }
            ) {

                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Bio",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (user.bio.isBlank())
                    "No bio added"
                else
                    user.bio
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = {

                    editedBio = user.bio
                    showBioDialog = true

                }
            ) {

                Text("Edit Bio")

            }

            Spacer(modifier = Modifier.height(30.dp))
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                    authViewModel.logout()

                    navController.navigate(Screen.Login.route) {

                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }

                        launchSingleTop = true

                    }

                }
            ) {

                Text("Logout")

            }

        }

        // Edit Name Dialog
        if (showNameDialog) {

            AlertDialog(

                onDismissRequest = {
                    showNameDialog = false
                },

                title = {
                    Text("Edit Name")
                },

                text = {

                    OutlinedTextField(

                        value = editedName,

                        onValueChange = {
                            editedName = it
                        },

                        label = {
                            Text("Full Name")
                        },

                        singleLine = true

                    )

                },

                confirmButton = {

                    TextButton(

                        onClick = {

                            authViewModel.updateUserName(

                                newName = editedName,

                                onSuccess = {

                                    Toast.makeText(
                                        context,
                                        "Name Updated",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    authViewModel.getUserData()

                                    showNameDialog = false

                                },

                                onFailure = { error ->

                                    Toast.makeText(
                                        context,
                                        error,
                                        Toast.LENGTH_LONG
                                    ).show()

                                }

                            )

                        }

                    ) {

                        Text("Save")

                    }

                },

                dismissButton = {

                    TextButton(
                        onClick = {
                            showNameDialog = false
                        }
                    ) {

                        Text("Cancel")

                    }

                }

            )

        }

        // Edit Bio Dialog
        if (showBioDialog) {

            AlertDialog(

                onDismissRequest = {
                    showBioDialog = false
                },

                title = {
                    Text("Edit Bio")
                },

                text = {

                    OutlinedTextField(

                        value = editedBio,

                        onValueChange = {
                            editedBio = it
                        },

                        label = {
                            Text("Bio")
                        }

                    )

                },

                confirmButton = {

                    TextButton(

                        onClick = {

                            authViewModel.updateUserBio(

                                newBio = editedBio,

                                onSuccess = {

                                    Toast.makeText(
                                        context,
                                        "Bio Updated",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    authViewModel.getUserData()

                                    showBioDialog = false

                                },

                                onFailure = { error ->

                                    Toast.makeText(
                                        context,
                                        error,
                                        Toast.LENGTH_LONG
                                    ).show()

                                }

                            )

                        }

                    ) {

                        Text("Save")

                    }

                },

                dismissButton = {

                    TextButton(
                        onClick = {
                            showBioDialog = false
                        }
                    ) {

                        Text("Cancel")

                    }

                }

            )

        }    }

}