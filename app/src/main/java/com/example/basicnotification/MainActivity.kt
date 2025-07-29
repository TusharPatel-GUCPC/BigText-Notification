package com.example.basicnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.basicnotification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var builder: NotificationCompat.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        createNotificationBuilder()
        createNotificationChannel()

        binding.button.setOnClickListener {
            // Step 3: Show notification
            with(NotificationManagerCompat.from(this)) {
                notify(0, builder.build())
            }
        }
    }

    //Step 1: create notification builder variable
    private fun createNotificationBuilder() {
        val bigText = "The Android operating system is constantly evolving. In this release, we've added features like improved battery life, enhanced privacy controls, and support for foldable devices. Developers can now take advantage of Jetpack Compose for faster UI development."

        builder = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("New Message")
            .setContentText("You have a new message from Tushar.")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(bigText)
                    .setBigContentTitle("Android 14 - What's New")
                    .setSummaryText("android-news.com")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    }

    //Step 2: create notification channel
    private fun createNotificationChannel() {
            val name = "Basic Notification Channel"
            val descriptionText = "Used for basic notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
    }
}