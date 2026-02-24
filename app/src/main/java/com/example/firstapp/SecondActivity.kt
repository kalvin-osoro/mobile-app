package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.ui.SecondScreen
import com.example.firstapp.ui.theme.FirstAppTheme

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val person = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("EXTRA_PERSON", Person::class.java)
        } else {
            intent.getSerializableExtra("EXTRA_PERSON") as? Person
        } ?: Person("", "", "", "")

        setContent {
            FirstAppTheme {
                SecondScreen(
                    person = person,
                    onBackClick = { finish() },
                    onLaunchThirdClick = {
                        Intent(this, ThirdActivity::class.java).also {
                            startActivity(it)
                        }
                    }
                )
            }
        }
    }
}
