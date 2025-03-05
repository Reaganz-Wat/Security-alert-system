package com.example.todoapp
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var powerButtonReceiver: PowerButtonReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        powerButtonReceiver = PowerButtonReceiver()

        // Register the receiver for power button events
        registerReceiver(
            powerButtonReceiver,
            IntentFilter(Intent.ACTION_SCREEN_OFF)
        )

        setContent {
            TodoAppTheme {
                AppNavigation()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(powerButtonReceiver)
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "sosMessaging") {
        composable("sosMessaging") {SOSMessagingScreen(navController)}
        composable("profile") {ProfileScreen(navController)}
        composable("contacts") {ContactsScreen(navController)}
        composable("activities") {ActivitiesScreen(navController)}
        composable("settings") {SettingsScreen(navController)}
        composable("logsDetails") { LogsDetails(navController) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SOSMessagingScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Hi Reagan", style = MaterialTheme.typography.titleLarge)
                }, actions = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .size(50.dp)
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // SOS Message Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(10.dp))){
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "SOS Icon",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "SOS Alert",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "be safe, be alert",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }

            Text(text = "All Menu")

            // Menu Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val menuItems = listOf(
                    MenuItem("Profile", Icons.Default.AccountCircle, "profile"),
                    MenuItem("Contacts", Icons.Default.Call, "contacts"),
                    MenuItem("Activities", Icons.Default.Info, "activities"),
                    MenuItem("Settings", Icons.Default.Settings, "settings"),
                    MenuItem("Edit Timer", Icons.Default.DateRange, "settings"),
                    MenuItem("Edit SOS Messages", Icons.Default.Edit, "settings")
                )

                items(menuItems) { item ->
                    MenuCard(item, navController)
                }
            }
        }
    }
}

data class MenuItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun MenuCard(item: MenuItem, navController: NavHostController) {
    Card(
        onClick = {
            navController.navigate(item.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                modifier = Modifier.size(70.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewModal() {
    InputModal()
}

@Composable
fun InputModal() {
    var text by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Column {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Contact Name") }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Contact Number") }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row (verticalAlignment = Alignment.Top) {
                Text("isActive", style = MaterialTheme.typography.bodyLarge)
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row () {
                Button(onClick = {}) {
                    Text("Close")
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(onClick = {}) {
                    Text("Save")
                }
            }
        }
    }
}