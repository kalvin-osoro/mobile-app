package com.example.firstapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.firstapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySecondBinding

    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val person = intent.getSerializableExtra("EXTRA_PERSON") as Person

        binding.tvSecondActivity.text = person.toString()

        //Launch third activity
        binding.btnLaunchThird.setOnClickListener() {
            Intent(this, ThirdActivity::class.java).also {
                startActivity(it)
            }

        }

        binding.btnBack.setOnClickListener() {
            finish()
        }

    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT).apply {

                lightColor = android.graphics.Color.GREEN
                enableLights(true)
            }
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}