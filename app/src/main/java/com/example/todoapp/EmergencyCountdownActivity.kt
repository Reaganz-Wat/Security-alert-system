package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.ui.theme.TodoAppTheme
import kotlinx.coroutines.delay

class EmergencyCountdownActivity : ComponentActivity() {
    private val TAG = "EmergencyCountdown"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get countdown seconds from intent
        val countdownSeconds = intent.getIntExtra("countdown_seconds", 5)
        Log.d(TAG, "Starting countdown for $countdownSeconds seconds")

        setContent {
            TodoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EmergencyCountdownScreen(
                        countdownSeconds = countdownSeconds,
                        onCancel = { cancelEmergency() }
                    )
                }
            }
        }
    }

    private fun cancelEmergency() {
        Log.d(TAG, "Emergency countdown cancelled by user")
        // Send broadcast to cancel emergency in service
        sendBroadcast(Intent("com.example.todoapp.CANCEL_EMERGENCY"))
        finish()
    }

    override fun onBackPressed() {
        // Cancel emergency when back button is pressed
        cancelEmergency()
        super.onBackPressed()
    }
}

@Composable
fun EmergencyCountdownScreen(
    countdownSeconds: Int,
    onCancel: () -> Unit
) {
    var secondsRemaining by remember { mutableIntStateOf(countdownSeconds) }
    val progress = remember(secondsRemaining) {
        (countdownSeconds - secondsRemaining).toFloat() / countdownSeconds
    }

    // Countdown timer effect
    LaunchedEffect(Unit) {
        while (secondsRemaining > 0) {
            delay(1000) // 1 second delay
            secondsRemaining--
        }
        // When countdown reaches zero, activity will be finished by the service
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "SOS ALERT EMERGENCY",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.error
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Sending emergency messages in:",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "$secondsRemaining",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )

                Spacer(modifier = Modifier.height(16.dp))

                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.error
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onCancel,
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text(
                        text = "CANCEL EMERGENCY",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}