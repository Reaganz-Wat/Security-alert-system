package com.example.todoapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi
import androidx.wear.compose.foundation.rememberRevealState
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.SwipeToRevealCard
import com.example.todoapp.database.Contact
import com.example.todoapp.viewModel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(navHostController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    var openModal by remember { mutableStateOf(false) }
    var contactToEdit by remember { mutableStateOf<Contact?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Contacts") },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        DropdownMenuItem(
                            onClick = { navHostController.navigate("settings") },
                            text = { Text("Settings") },
                            leadingIcon = {
                                Icon(Icons.Default.Settings, "Settings")
                            }
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                contactToEdit = null
                openModal = true
            }) {
                Icon(Icons.Default.Add, "Add Contact")
            }
        }
    ) { paddingValues ->
        ContactsScreenContent(
            modifier = Modifier.padding(paddingValues),
            openModal = openModal,
            onCloseModal = { openModal = false },
            contactToEdit = contactToEdit,
            onEditContact = { contact ->
                contactToEdit = contact
                openModal = true
            }
        )
    }
}

@Composable
private fun ContactsScreenContent(
    modifier: Modifier = Modifier,
    openModal: Boolean,
    onCloseModal: () -> Unit,
    contactToEdit: Contact?,
    onEditContact: (Contact) -> Unit,
    viewModel: ContactViewModel = viewModel()
) {
    val contacts by viewModel.allContacts.collectAsState(initial = emptyList())

    Column(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            items(
                items = contacts,
                key = { contact -> contact.uid }
            ) { contact ->
                ContactCard(
                    contact = contact,
                    onDelete = { viewModel.deleteContacts(contact) },
                    onEdit = { onEditContact(contact) }
                )
            }
        }

        if (openModal) {
            ContactDialog(
                contact = contactToEdit,
                onDismiss = {
                    onCloseModal()
                },
                onSave = { name, number, isActive ->
                    if (contactToEdit != null) {
                        viewModel.updateContacts(
                            contactToEdit.copy(
                                name = name,
                                contact = number,
                                isActive = isActive
                            )
                        )
                    } else {
                        viewModel.addContacts(
                            Contact(
                                name = name,
                                contact = number,
                                isActive = isActive
                            )
                        )
                    }
                    onCloseModal()
                }
            )
        }
    }
}

@OptIn(ExperimentalWearFoundationApi::class, ExperimentalWearMaterialApi::class)
@Composable
private fun ContactCard(
    contact: Contact,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    val revealState = rememberRevealState()

    SwipeToRevealCard(
        revealState = revealState,
        primaryAction = {
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, "Delete")
            }
        },
        onFullSwipe = {
            onDelete()
            // Handle full swipe action
        },
        secondaryAction = {
            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(modifier = Modifier.padding(10.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_account_circle_24),
                    contentDescription = "Contact Image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Column {
                    Text(contact.name, style = MaterialTheme.typography.bodyMedium)
                    Text("+256 ${contact.contact}", style = MaterialTheme.typography.bodyMedium)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("isActive", style = MaterialTheme.typography.bodyMedium)
                        Checkbox(checked = contact.isActive, onCheckedChange = {})
                    }
                }
            }
        }
    }
}

@Composable
private fun ContactDialog(
    contact: Contact?,
    onDismiss: () -> Unit,
    onSave: (name: String, number: String, isActive: Boolean) -> Unit
) {
    var name by remember { mutableStateOf(contact?.name ?: "") }
    var number by remember { mutableStateOf(contact?.contact ?: "") }
    var isActive by remember { mutableStateOf(contact?.isActive ?: false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = if (contact == null) "Add Contact" else "Edit Contact",
                    style = MaterialTheme.typography.titleLarge
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = number,
                    onValueChange = { number = it },
                    label = { Text("Phone Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Is Active")
                    Checkbox(
                        checked = isActive,
                        onCheckedChange = { isActive = it }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancel")
                    }
                    Button(
                        onClick = { onSave(name, number, isActive) },
                        modifier = Modifier.weight(1f),
                        enabled = name.isNotBlank() && number.isNotBlank()
                    ) {
                        Text(if (contact == null) "Add" else "Update")
                    }
                }
            }
        }
    }
}