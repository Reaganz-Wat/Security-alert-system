package com.example.todoapp

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.todoapp.database.SOSMessage
import com.example.todoapp.viewModel.ContactViewModel

@Composable
fun EditMessageContent(modifier: Modifier = Modifier, viewModal: ContactViewModel = viewModel()) {

    val context = LocalContext.current
    val SOSMessageDeails by viewModal.SOSMessage.collectAsState(initial = emptyList())


    // State for the message
    var message by remember {
        mutableStateOf("")
    }

    LaunchedEffect (SOSMessageDeails) {
        if (SOSMessageDeails.isNotEmpty()) {
            message = SOSMessageDeails[0].message
        } else {
            Toast.makeText(context, "There is no message available in database", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title or description of the screen
        Text(
            text = "Edit SOS Message directly here",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp) // Space between title and text field
        )

        // OutlinedTextField for message input (multi-line text area)
        OutlinedTextField(
            value = message,
            onValueChange = { message = it }, // Update message state when text changes
            label = { Text("Enter your message here") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp), // Space between text field and button
            maxLines = 5, // Max number of lines (height of the text area)
            minLines = 3, // Minimum number of lines
            placeholder = { Text("Type your message...") },
            singleLine = false // Ensure it's multi-line
        )

        Spacer(modifier = Modifier.height(10.dp))

        // OutlinedButton to save the message
        Button (
            onClick = {
                var messageInfo = SOSMessage(message = message)
                if (SOSMessageDeails.isNotEmpty()) {
                    messageInfo = SOSMessageDeails[0].copy(message = message)
                    viewModal.editSOSMessage(messageInfo) // Fixing function call
                } else {
                    viewModal.createSOSMessage(messageInfo)
                }
                Toast.makeText(context, "Message Updated Successfully", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth() // Make the button span the entire width
        ) {
            Text("Save Changes")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSOSMessage(navController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Edit SOS Message")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back")
                    }
                }
            )
        }
    ) {
            innerPadding -> EditMessageContent (modifier = Modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewMessaging() {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text("Edit SOS Message")
                },
                navigationIcon = {
                    IconButton(onClick = {
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back")
                    }
                }
            )
        }
    ) {
            innerPadding -> EditMessageContent (modifier = Modifier.padding(innerPadding))
    }
}