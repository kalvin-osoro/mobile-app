package com.example.firstapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val getContent = registerForActivityResult(GetContent) {
//            uri: Uri?->
//            binding.ivImage.setImageURI(uri)
//        }

//        val getContent = Intent.apply


        //implicit intents
        val imageView = binding.ivImage
        val content = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data?.data
                imageView.setImageURI(data)
            }
        }

        binding.btnLauchImg.setOnClickListener() {

           Intent(Intent.ACTION_GET_CONTENT).also {
              it.type = "image/*"
               content.launch(it)

            }
        }





    }
}