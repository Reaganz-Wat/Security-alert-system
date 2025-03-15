//package com.example.todoapp
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.util.Log
//
//class PowerButtonReceiver : BroadcastReceiver() {
//    private val pressTimeWindow = 1000L // 1 second window for consecutive presses
//    private val requiredPresses = 3
//    private var lastPressTime = 0L
//    private var pressCount = 0
//
//    override fun onReceive(context: Context, intent: Intent) {
//        Log.d("PowerButtonReceiver", "onReceive called with action: ${intent.action}")
//
//        // Check for both screen on and off actions
//        if (intent.action == Intent.ACTION_SCREEN_OFF ||
//            intent.action == Intent.ACTION_SCREEN_ON) {
//
//            val currentTime = System.currentTimeMillis()
//            val timeSinceLastPress = currentTime - lastPressTime
//
//            Log.d("PowerButtonReceiver", "Power button pressed. Time since last press: $timeSinceLastPress ms")
//
//            if (currentTime - lastPressTime <= pressTimeWindow) {
//                pressCount++
//                Log.d("PowerButtonReceiver", "Press within time window. Press count: $pressCount")
//
//                if (pressCount >= requiredPresses) {
//                    Log.d("PowerButtonReceiver", "Required press count reached! Launching messaging app.")
//                    // Reset counter
//                    pressCount = 0
//                    // Launch messaging app
//                    launchMessagingApp(context)
//                }
//            } else {
//                // Reset counter if time window exceeded
//                Log.d("PowerButtonReceiver", "Time window exceeded. Resetting press count to 1")
//                pressCount = 1
//            }
//            lastPressTime = currentTime
//        }
//    }
//
//    private fun launchMessagingApp(context: Context) {
//        Log.d("PowerButtonReceiver", "Attempting to launch messaging app")
//        val intent = Intent(Intent.ACTION_MAIN).apply {
//            addCategory(Intent.CATEGORY_APP_MESSAGING)
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        }
//        try {
//            context.startActivity(intent)
//            Log.d("PowerButtonReceiver", "Messaging app launch successful")
//        } catch (e: Exception) {
//            Log.e("PowerButtonReceiver", "Failed to launch messaging app", e)
//        }
//    }
//}



package com.example.todoapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class PowerButtonReceiver : BroadcastReceiver() {
    private val pressTimeWindow = 1000L // 1 second window for consecutive presses
    private val requiredPresses = 3
    private var lastPressTime = 0L
    private var pressCount = 0
    private val countdownSeconds = 5 // Default countdown time

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("PowerButtonReceiver", "onReceive called with action: ${intent.action}")

        // Check for both screen on and off actions
        if (intent.action == Intent.ACTION_SCREEN_OFF ||
            intent.action == Intent.ACTION_SCREEN_ON) {

            val currentTime = System.currentTimeMillis()
            val timeSinceLastPress = currentTime - lastPressTime

            Log.d("PowerButtonReceiver", "Power button pressed. Time since last press: $timeSinceLastPress ms")

            if (currentTime - lastPressTime <= pressTimeWindow) {
                pressCount++
                Log.d("PowerButtonReceiver", "Press within time window. Press count: $pressCount")

                if (pressCount >= requiredPresses) {
                    Log.d("PowerButtonReceiver", "Required press count reached! Starting emergency countdown.")
                    // Reset counter
                    pressCount = 0
                    // Start emergency countdown
                    startEmergencyCountdown(context)
                }
            } else {
                // Reset counter if time window exceeded
                Log.d("PowerButtonReceiver", "Time window exceeded. Resetting press count to 1")
                pressCount = 1
            }
            lastPressTime = currentTime
        }
    }

    private fun startEmergencyCountdown(context: Context) {
        Log.d("PowerButtonReceiver", "Starting emergency countdown")

        // Launch the EmergencyCountdownActivity
        val intent = Intent(context, EmergencyCountdownActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("countdown_seconds", countdownSeconds)
        }

        try {
            context.startActivity(intent)
            Log.d("PowerButtonReceiver", "Emergency countdown activity launched")
        } catch (e: Exception) {
            Log.e("PowerButtonReceiver", "Failed to launch emergency countdown", e)
        }
    }
}