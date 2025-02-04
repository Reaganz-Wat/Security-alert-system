package com.example.todoapp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.todoapp.database.Contact
import com.example.todoapp.viewModel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ContactsScreen(navHostController: NavHostController) {


    var expanded by remember { mutableStateOf(false) }
    var openModal by remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Contacts")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "ArrowBack")
                    }
                },
                actions = {
                    Row{
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                        }
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Add Contacts"
                        )
                    }
                }
                    DropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}) {
                        DropdownMenuItem(onClick = {
                            navHostController.navigate("settings")
                        }, text = { Text("Settings") }, leadingIcon = {
                            Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
                        })
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                openModal = !openModal
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Contacts")
            }
        }
    ) {
            innerPadding -> ContactsScreenContent(modifier = Modifier.padding(innerPadding), openModal, {openModal = !openModal})

    }
}


@Composable
fun ContactsScreenContent(
    modifier: Modifier,
    openModal: Boolean,
    onDismiss: () -> Unit,
    contactsViewModal: ContactViewModel = viewModel()
) {

    val contactsList by contactsViewModal.allContacts.collectAsState(initial = emptyList())


    Column (modifier = modifier) {
        Spacer(modifier = Modifier.height(10.dp))
        Row{
            Spacer(modifier = Modifier.size(10.dp))
            Text("Emergency Contacts", style = MaterialTheme.typography.titleLarge)
        }

        val contactList = listOf(
            ContactObject("Unity Kate", 780807123, R.drawable.logo, true),
            ContactObject("Reagan Watmon", 780807525, R.drawable.img, false)
        )

        LazyColumn (modifier = Modifier.fillMaxWidth()) {
            items(contactsList) {
                contact -> ContactsCard(contact)
            }
        }


        if (openModal) {
            AddContactDialog(onDismiss, contactsViewModal)
        }
    }

}

@Composable
fun AddContactDialog(onDismiss: () -> Unit, viewModel: ContactViewModel) {
    Dialog(onDismissRequest = {onDismiss()}) {
        AddContactModal(onDismiss, viewModel)
    }
}

data class ContactObject(
    val name: String, val phone_number: Long, val image: Int, val isActive: Boolean
)

data class ContactObjectModified(
    val name: String,
    val contact: String,
    val isActive: Boolean
)

@Composable
fun ContactsCard(item: Contact) {
    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.padding(10.dp)){
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(
                        CircleShape
                    )
            )
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(item.name, style = MaterialTheme.typography.bodyMedium)
                Text("+256 ${item.contact}", style = MaterialTheme.typography.bodyMedium)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("isActive", style = MaterialTheme.typography.bodyMedium)
                    Checkbox(checked = item.isActive, onCheckedChange = {})
                }
            }
        }
    }
}

@Composable
fun AddContactModal(
    onDismiss: () -> Unit, viewModel: ContactViewModel
) {

    var contactName by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }

    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), shape = RoundedCornerShape(10.dp)) {
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Text("Add Contact", style = MaterialTheme.typography.titleLarge)
            OutlinedTextField(value = contactName, onValueChange = {contactName = it}, label = { Text("Contact Name") })
            OutlinedTextField(
                value = contactNumber,
                onValueChange = {contactNumber = it},
                label = { Text("Contact Number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text("isActive", style = MaterialTheme.typography.bodyLarge)
                Checkbox(checked = checked, onCheckedChange = {checked = !checked})
            }
            Row (modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(onClick = {onDismiss()}, shape = RoundedCornerShape(8.dp), modifier = Modifier.weight(1f)) {
                    Text("Close")
                }
                Spacer(modifier = Modifier.size(10.dp))
                Button(onClick = {
                    val contact = Contact(name = contactName, contact = contactNumber, isActive = checked)
                    viewModel.addContacts(contact)
                    onDismiss()
                }) {
                    Text("Save")
                }
            }
        }
    }
}