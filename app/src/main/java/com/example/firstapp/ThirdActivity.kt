package com.example.firstapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.*
import com.example.firstapp.ui.ThirdScreen
import com.example.firstapp.ui.theme.FirstAppTheme

class ThirdActivity : AppCompatActivity() {

    private var selectedImageUri by mutableStateOf<Uri?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                selectedImageUri = result.data?.data
            }
        }

        setContent {
            FirstAppTheme {
                ThirdScreen(
                    selectedImageUri = selectedImageUri,
                    onSelectImageClick = {
                        Intent(Intent.ACTION_GET_CONTENT).also {
                            it.type = "image/*"
                            contentLauncher.launch(it)
                        }
                    }
                )
            }
        }
    }
}
