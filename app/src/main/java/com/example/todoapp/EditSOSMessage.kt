package com.example.todoapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.OutlinedButton

@Composable
fun MainContent(modifier: Modifier = Modifier) {

    // State for the message
    var message by remember { mutableStateOf("") }

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
        OutlinedButton(
            onClick = {
                // Handle Save logic here (e.g., save to a database or show a confirmation)
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
            innerPadding -> MainContent (modifier = Modifier.padding(innerPadding))
    }
}