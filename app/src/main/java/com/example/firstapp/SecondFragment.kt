package com.example.firstapp

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import androidx.fragment.app.Fragment
import com.example.firstapp.ui.SecondFragmentScreen
import com.example.firstapp.ui.theme.FirstAppTheme

class SecondFragment : Fragment() {

    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"
    val NOTIFICATION_ID = 0
    private val NOTIFICATION_PERMISSION_REQUEST_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FirstAppTheme {
                    SecondFragmentScreen(onShowNotificationClick = {
                        showNotification()
                    })
                }
            }
        }
    }

    private fun showNotification() {
        createNotificationChannel()

        val intent = Intent(requireContext(), FragmentActivity::class.java).apply {
            putExtra("navigate_to", "SecondFragment")
        }
        val pendingIntent = TaskStackBuilder.create(requireContext()).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)
        }

        val notification = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setContentTitle("Notification popup!")
            .setContentText("This is a popup notification")
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager = NotificationManagerCompat.from(requireContext())

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(NOTIFICATION_ID, notification)
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                NOTIFICATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor = android.graphics.Color.GREEN
                enableLights(true)
            }
            val manager = requireContext().getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}
