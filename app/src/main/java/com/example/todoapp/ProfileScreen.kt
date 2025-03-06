package com.example.todoapp

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.todoapp.database.Profile
import com.example.todoapp.viewModel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Profile")
                },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Arrow")
                    }
                }
            )
        }
    ) {
            innerPadding -> ProfileMainContent(modifier = Modifier.padding(innerPadding))
    }
}


@Composable
fun ProfileMainContent(modifier: Modifier, viewModel: ContactViewModel = viewModel()) {

    var openDialog by remember { mutableStateOf(false) }
    val profileDetails by viewModel.profileInfo.collectAsState(initial = emptyList())

// Default empty values
    val defaultProfile = Profile(
        name = "",
        email = "",
        contact = "",
        address = ""
    )

    // Safely get profile or use default
    val profile = profileDetails.firstOrNull() ?: defaultProfile

    // Use remember with default values from profile
    var name by remember { mutableStateOf(profile.name ?: "") }
    var email by remember { mutableStateOf(profile.email ?: "") }
    var contact by remember { mutableStateOf(profile.contact ?: "") }
    var address by remember { mutableStateOf(profile.address ?: "") }

    // Update the values whenever profile changes
    LaunchedEffect (profile) {
        name = profile.name ?: ""
        email = profile.email ?: ""
        contact = profile.contact ?: ""
        address = profile.address ?: ""
    }

    Column (modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(20.dp))
        Surface (
            shape = CircleShape,
            border = BorderStroke(6.dp, MaterialTheme.colorScheme.primary)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(150.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))

        Text(text = name, style = MaterialTheme.typography.titleLarge)

        Card (modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column (horizontalAlignment = Alignment.Start, modifier = Modifier.padding(10.dp)) {
                CardText("Name:", name)
                CardText("Email:", email)
                CardText("Contact:", contact)
                CardText("Address:", address)
            }

            Button(onClick = {openDialog = !openDialog}, modifier = Modifier.padding(start = 10.dp, bottom = 10.dp), shape = RoundedCornerShape(10.dp)) {
                Text("Edit Profile")
            }
        }

        if (openDialog) {
            ProfileModal({openDialog = !openDialog}, viewModel = viewModel, currentProfile = profileDetails[0])
        }
    }
}

@Composable
fun CardText(title: String, value: String) {
    Spacer(modifier = Modifier.height(2.dp))
    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = value, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun ProfileModal(onDismiss: () -> Unit, viewModel: ContactViewModel, currentProfile: Profile) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        EditProfileFields(onDismiss, viewModel = viewModel, currentProfile = currentProfile)
    }
}

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EditProfileFields(onDismiss: () -> Unit, viewModel: ContactViewModel,currentProfile: Profile) {

    var name by remember { mutableStateOf(currentProfile.name) }
    var email by remember { mutableStateOf(currentProfile.email) }
    var contact by remember { mutableStateOf(currentProfile.contact) }
    var address by remember { mutableStateOf(currentProfile.address) }



    val context = LocalContext.current

    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Column (modifier = Modifier.padding(10.dp)) {
            Text("Edit Profile", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = name,
                label = { Text("Name") },
                onValueChange = {name = it},
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = email,
                label = { Text("Email") },
                onValueChange = {email = it},
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = contact,
                label = { Text("Contact") },
                onValueChange = {contact = it},
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = address,
                label = { Text("Address") },
                onValueChange = {address = it},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(
                    onClick = {onDismiss()},
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Close")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = {
                        val updatedProfile = currentProfile.copy(
                            name = name,
                            email = email,
                            contact = contact,
                            address = address
                        )

                        if (updatedProfile.uid != null) {
                            viewModel.updateProfile(updatedProfile)
                        } else {
                            viewModel.createProfile(updatedProfile)
                        }

                        // show some tast
                        Toast.makeText(context, "Profile Updated Successfully", Toast.LENGTH_SHORT).show()

                        onDismiss()},
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Save")
                }
            }
        }
    }
}