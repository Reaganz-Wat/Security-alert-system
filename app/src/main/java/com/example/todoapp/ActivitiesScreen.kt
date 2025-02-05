package com.example.todoapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivitiesScreen(navController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text("Activity Logs")
                },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back")
                    }
                },actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "search bar")
                    }
                }
            )
        },

        ) {
            innerPadding -> MainActivityContent(modifier = Modifier.padding(innerPadding), navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ActivityScreenPreview(navController: NavHostController
) {
    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Activity Logs")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "search bar")
                    }
                }
            )
        },

    ) {
        innerPadding -> MainActivityContent(modifier = Modifier.padding(innerPadding), navController)
    }
}

@Composable
fun MainActivityContent(modifier: Modifier, navController: NavHostController) {
    LazyColumn (modifier = Modifier.fillMaxSize()) {
        items(activitiesList) {
            activity -> ActivityCard(activity, navController)
        }
    }
}

@Composable
fun ActivityCard(activityData: ActivitiesScreenDetails, navController: NavHostController) {
    Card(
        onClick = {navController.navigate("logsDetails")},
        modifier = Modifier.fillMaxWidth().padding(10.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
    Column (modifier = Modifier.padding(10.dp)) {
        CardActivityText("Date", activityData.date)
        CardActivityText("Time", activityData.time)
        CardActivityText("Location", activityData.location)
    }
    }
}

@Composable
fun CardActivityText(title: String, value: String) {
    Spacer(modifier = Modifier.height(1.dp))
    Text(text = "$title: $value", style = MaterialTheme.typography.bodyLarge)
}