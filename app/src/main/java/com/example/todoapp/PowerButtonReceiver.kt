package com.example.todoapp
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class PowerButtonReceiver : BroadcastReceiver() {
    private val pressTimeWindow = 1000L // 1 second window for consecutive presses
    private val requiredPresses = 3
    private var lastPressTime = 0L
    private var pressCount = 0

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_SCREEN_OFF) {
            val currentTime = System.currentTimeMillis()

            if (currentTime - lastPressTime <= pressTimeWindow) {
                pressCount++
                if (pressCount >= requiredPresses) {
                    // Reset counter
                    pressCount = 0
                    // Launch messaging app
                    launchMessagingApp(context)
                }
            } else {
                // Reset counter if time window exceeded
                pressCount = 1
            }
            lastPressTime = currentTime
        }
    }

    private fun launchMessagingApp(context: Context) {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_APP_MESSAGING)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }
}