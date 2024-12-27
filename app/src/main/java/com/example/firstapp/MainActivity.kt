package com.example.firstapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import androidx.activity.ComponentActivity

import com.example.firstapp.databinding.ActivityMainBinding
//import timber.log.Timber
import com.example.firstapp.ui.theme.FirstAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnApply.setOnClickListener() {

            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val birthDate = binding.etBirthDate.text.toString()
            val country = binding.etCountry.text.toString()

            Log.d("MainActivity", "$firstName $lastName born on $birthDate, from $country judt applied to the formular")


            binding.btnOrder.setOnClickListener {
                println("Button clicked!")
                val checkedMeatRadioButtonId = binding.rgMeat.checkedRadioButtonId
                val meat = findViewById<RadioButton>(checkedMeatRadioButtonId)
                val cheese = binding.cbCheese.isChecked
                val onions = binding.cbOnions.isChecked
                val salad = binding.cbSalad.isChecked
                val orderString = "You ordered a burger with:\n" +
                        "${meat?.text ?: "No meat selected"}" +
                        (if(cheese) "\nCheese" else "") +
                        (if(onions) "\nOnions" else "") +
                        (if (salad) "\nSalad" else "")
                binding.tvOrder.text = orderString

                Log.d("Main", orderString)
            }
        }

    }
}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    FirstAppTheme {
//        Greeting("Android")
//    }
//}