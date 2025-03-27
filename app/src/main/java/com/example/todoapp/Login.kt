//package com.example.todoapp
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Close
//import androidx.compose.material.icons.filled.Email
//import androidx.compose.material.icons.filled.Lock
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Divider
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedButton
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.vectorResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import androidx.navigation.NavHost
//import androidx.navigation.NavHostController
//import com.example.todoapp.viewModel.MyviewModel
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewLogin() {
////    Login()
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Login(navController: NavHostController) {
//    Scaffold { innerPadding ->
//        LoginContent(modifier = Modifier.padding(innerPadding), navController)
//    }
//}
//
//@Composable
//fun LoginContent(modifier: Modifier = Modifier, navController: NavHostController, viewModel: MyviewModel = viewModel()) {
//    // States
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var passwordVisible by remember { mutableStateOf(false) }
//
//    // all users
//    var allUsers = viewModel.allUsers.collectAsState(initial = emptyList())
//
//    Box(
//        modifier = modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.background)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(20.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            // App Logo
//            Box(
//                modifier = Modifier
//                    .size(80.dp)
//                    .clip(CircleShape)
//                    .background(MaterialTheme.colorScheme.primary)
//                    .padding(16.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Lock,
//                    contentDescription = "App Logo",
//                    tint = Color.White,
//                    modifier = Modifier.size(40.dp)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Login Header
//            Text(
//                text = "Login to SOS Security Alert",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                color = MaterialTheme.colorScheme.primary
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = "Stay safe with real-time security alerts",
//                fontSize = 14.sp,
//                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
//                textAlign = TextAlign.Center
//            )
//
//            Spacer(modifier = Modifier.height(32.dp))
//
//            // Email field
//            OutlinedTextField(
//                modifier = Modifier.fillMaxWidth(),
//                value = email,
//                onValueChange = { email = it },
//                label = { Text("Email") },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Email,
//                        contentDescription = "Email Icon"
//                    )
//                },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                shape = RoundedCornerShape(12.dp)
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Password field
//            OutlinedTextField(
//                modifier = Modifier.fillMaxWidth(),
//                value = password,
//                onValueChange = { password = it },
//                label = { Text("Password") },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Lock,
//                        contentDescription = "Password Icon"
//                    )
//                },
//                trailingIcon = {
//                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                        Icon(
//                            imageVector = ImageVector.vectorResource( if (passwordVisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24),
//                            contentDescription = if (passwordVisible) "Hide Password" else "Show Password"
//                        )
//                    }
//                },
//                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                shape = RoundedCornerShape(12.dp)
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // Forgot password
//            TextButton(
//                onClick = {
//                    navController.navigate("forgot password")
//                },
//                modifier = Modifier.align(Alignment.End)
//            ) {
//                Text(
//                    text = "Forgot Password?",
//                    color = MaterialTheme.colorScheme.primary
//                )
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Login Button
//            Button(
//                onClick = {
//                    navController.navigate("sosMessaging")
//                },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                elevation = ButtonDefaults.buttonElevation(
//                    defaultElevation = 6.dp
//                )
//            ) {
//                Text(
//                    text = "LOGIN",
//                    modifier = Modifier.padding(vertical = 8.dp),
//                    fontWeight = FontWeight.Bold
//                )
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Or divider
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Divider(
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(end = 8.dp)
//                )
//                Text(
//                    text = "OR",
//                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
//                    fontSize = 12.sp
//                )
//                Divider(
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(start = 8.dp)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Social Login Buttons
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                // Google Login
//                OutlinedButton(
//                    onClick = { /* Google login action */ },
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(end = 8.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    border = ButtonDefaults.outlinedButtonBorder
//                ) {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        // Replace with actual Google icon
//                        Box(
//                            modifier = Modifier
//                                .size(24.dp)
//                                .background(Color.Red, CircleShape),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text("G", color = Color.White, fontWeight = FontWeight.Bold)
//                        }
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Text("Google")
//                    }
//                }
//
//                // Facebook Login
//                OutlinedButton(
//                    onClick = { /* Facebook login action */ },
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(start = 8.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    border = ButtonDefaults.outlinedButtonBorder
//                ) {
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        // Replace with actual Facebook icon
//                        Box(
//                            modifier = Modifier
//                                .size(24.dp)
//                                .background(Color.Blue, CircleShape),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text("f", color = Color.White, fontWeight = FontWeight.Bold)
//                        }
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Text("Facebook")
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Sign up suggestion
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = "Don't have an account?",
//                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
//                )
//                TextButton(onClick = {
//                    navController.navigate("signup")
//                }) {
//                    Text(
//                        text = "Sign Up",
//                        fontWeight = FontWeight.Bold,
//                        color = MaterialTheme.colorScheme.primary
//                    )
//                }
//            }
//        }
//    }
//}




package com.example.todoapp

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.todoapp.viewModel.MyviewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLogin() {
//    Login()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavHostController) {
    Scaffold { innerPadding ->
        LoginContent(modifier = Modifier.padding(innerPadding), navController)
    }
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MyviewModel = viewModel()
) {
    // States
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    // Coroutine scope for handling async operations
    val coroutineScope = rememberCoroutineScope()

    // all users
    var allUsers = viewModel.allUsers.collectAsState(initial = emptyList())

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // App Logo
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "App Logo",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Login Header
            Text(
                text = "Login to SOS Security Alert",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Stay safe with real-time security alerts",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                textAlign = TextAlign.Center
            )

            // Display login error if exists
            loginError?.let { error ->
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Email field
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = {
                    email = it
                    loginError = null // Clear error when user starts typing
                },
                label = { Text("Email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon"
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                shape = RoundedCornerShape(12.dp),
                isError = loginError != null
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password field
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = {
                    password = it
                    loginError = null // Clear error when user starts typing
                },
                label = { Text("Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon"
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                if (passwordVisible) R.drawable.baseline_visibility_24
                                else R.drawable.baseline_visibility_off_24
                            ),
                            contentDescription = if (passwordVisible) "Hide Password" else "Show Password"
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(12.dp),
                isError = loginError != null
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Forgot password
            TextButton(
                onClick = {
                    navController.navigate("forgot password")
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Forgot Password?",
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Login Button
            Button(
                onClick = {
                    // Validate inputs
                    if (email.isBlank() || password.isBlank()) {
                        loginError = "Please enter both email and password"
                        return@Button
                    }

                    // Attempt login
                    coroutineScope.launch(Dispatchers.IO) {
                        try {
                            // Assuming getUserByEmailAndPassword returns a user or null
                            val user = viewModel.getUserByEmailAndPassword(email, password)
                            Log.d("", "")
                            withContext(Dispatchers.Main){
                                if (user != null) {
                                    // Successful login

                                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT)
                                        .show()
                                    navController.navigate("sosMessaging") {
                                        // Clear back stack to prevent going back to login
                                        popUpTo(navController.graph.startDestinationId) {
                                            inclusive = true
                                        }
                                    }

                                } else {
                                    // Login failed
                                    loginError = "Invalid email or password"
                                    Toast.makeText(
                                        context,
                                        "Invalid email or password",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        } catch (e: Exception) {
                            // Handle any network or database errors
                            withContext(Dispatchers.Main){
                                loginError = "Login failed. Please try again."
                                Toast.makeText(
                                    context,
                                    "Invalid email or password",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 6.dp
                )
            ) {
                Text(
                    text = "LOGIN",
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Or divider
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "OR",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    fontSize = 12.sp
                )
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Social Login Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Google Login
                OutlinedButton(
                    onClick = { /* Google login action */ },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = ButtonDefaults.outlinedButtonBorder
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // Replace with actual Google icon
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(Color.Red, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("G", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Google")
                    }
                }

                // Facebook Login
                OutlinedButton(
                    onClick = { /* Facebook login action */ },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = ButtonDefaults.outlinedButtonBorder
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // Replace with actual Facebook icon
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(Color.Blue, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("f", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Facebook")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sign up suggestion
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account?",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
                TextButton(onClick = {
                    navController.navigate("signup")
                }) {
                    Text(
                        text = "Sign Up",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}