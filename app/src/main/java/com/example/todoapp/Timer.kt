//package com.example.todoapp
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.Button
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Timer(navHostController: NavHostController) {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
//                ),
//                title = {
//                    Text("Timer")
//                },
//                navigationIcon = {
//                    IconButton(onClick = {
//                        navHostController.popBackStack()
//                    }) {
//                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back")
//                    }
//                }
//            )
//        }
//    ) {
//            innerPadding -> TimerMainContent(modifier = Modifier.padding(innerPadding))
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ShowTimerContent() {
//    TimerContent()
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TimerContent() {
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
//                ),
//                title = {
//                    Text("Timer", color = Color.Black)
//                },
//                navigationIcon = {
//                    IconButton(onClick = {}) {
//                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back")
//                    }
//                }
//            )
//        }
//    ) {
//        innerPadding -> TimerMainContent(modifier = Modifier.padding(innerPadding))
//    }
//}
//
//@Composable
//fun TimerMainContent(modifier: Modifier = Modifier) {
//    var timer by remember { mutableStateOf("") }
//    Box(
//        modifier = modifier.fillMaxSize().padding(top = 10.dp, start = 10.dp),  // Fill the whole screen
//    ){
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//        ) {
//
//            OutlinedTextField(
//                value = timer,
//                onValueChange = { timer = it },
//                placeholder = { Text("Enter Number") }
//            )
//            Button(onClick = {}) {
//                Text("Save")
//            }
//        }
//    }
//}
//




package com.example.todoapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerSettingsScreen(navHostController: NavHostController?) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Timer Settings", color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = { navHostController?.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        TimerSettingsContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun TimerSettingsContent(modifier: Modifier = Modifier) {
    var minutes by remember { mutableStateOf(TextFieldValue("")) }
    var seconds by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Set Timer Duration", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = minutes,
                onValueChange = { minutes = it },
                label = { Text("Minutes") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
            )
            OutlinedTextField(
                value = seconds,
                onValueChange = { seconds = it },
                label = { Text("Seconds") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { /* Save logic here */ }) {
            Text("Save Timer Settings")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTimerSettings() {
    TimerSettingsScreen(navHostController = null)
}
