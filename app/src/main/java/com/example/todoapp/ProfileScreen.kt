package com.example.todoapp

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController

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
            innerPadding -> MainContent(modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TestingProfileScreen() {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text("Profile")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Arrow")
                    }
                }
            )
        }
    ) {
        innerPadding -> MainContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun MainContent(modifier: Modifier) {

    var openDialog by remember { mutableStateOf(false) }

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

        Text(text = "Watmon Reagan", style = MaterialTheme.typography.titleLarge)

        Card (modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column (horizontalAlignment = Alignment.Start, modifier = Modifier.padding(10.dp)) {
                CardText("Name:", "Watmon Reagan")
                CardText("Email:", "reaganwatmon6@gmail.com")
                CardText("Contact:", "0780807525")
                CardText("Address:", "Bardege Airfield")
            }

            Button(onClick = {openDialog = !openDialog}, modifier = Modifier.padding(start = 10.dp, bottom = 10.dp), shape = RoundedCornerShape(10.dp)) {
                Text("Edit Profile")
            }
        }

        if (openDialog) {
            ProfileModal({openDialog = !openDialog})
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
fun ProfileModal(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        EditProfileFields(onDismiss)
    }
}

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EditProfileFields(onDismiss: () -> Unit) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

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
                    onClick = {onDismiss()},
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Save")
                }
            }
        }
    }
}