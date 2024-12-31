package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySecondBinding

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
}