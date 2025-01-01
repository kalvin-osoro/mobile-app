package com.example.firstapp

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import com.example.firstapp.databinding.ActivityMainBinding
import com.example.firstapp.databinding.CustomToastBinding

//class MainActivity : ComponentActivity() {
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnApply.setOnClickListener() {
            val firstName = binding.etFirstName.text.toString()
            val secondName = binding.etLastName.text.toString()
            val birthDate = binding.etBirthDate.text.toString()
            val country = binding.etCountry.text.toString()

            val person = Person(firstName, secondName, birthDate, country)

            Log.d("MainActivity", "$firstName $secondName born on $birthDate, from $country just applied to the formular")

            Intent(this, SecondActivity::class.java).also {
                it.putExtra("EXTRA_PERSON", person)
                startActivity(it)
            }

        }

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

        binding.btnShowToast.setOnClickListener() {
            Toast.makeText(this, "Hi, I'm a toast!", Toast.LENGTH_LONG).show()
        }

        binding.btnCustomToast.setOnClickListener() {
            Toast(this).apply {
                duration = Toast.LENGTH_LONG
                setGravity(Gravity.BOTTOM, 0, 100)
                view = CustomToastBinding.inflate(layoutInflater).root
                show()
            }
        }

        binding.btnOpenActivity.setOnClickListener() {
            requestPermissions()
        }

        //Alert Dialog
        val addContactDialog = AlertDialog.Builder(this)
            .setTitle("Add Contact")
            .setMessage("Do you want to add Jane to your contacts")
            .setIcon(R.drawable.ic_add_contact)
            .setPositiveButton("yes"){ _, _ ->
                Toast.makeText(this, "You added Jane to your contacts", Toast.LENGTH_SHORT).show()

            }
            .setNegativeButton("No"){ _, _ ->
                Toast.makeText(this, "You did not add Jane to your contacts", Toast.LENGTH_SHORT).show()
            }.create()

        binding.btnDialog1.setOnClickListener() {
            addContactDialog.show()
        }

        //single choice dialog
        val options = arrayOf("First item", "Second Item", "Third Item")
        val singleChoiceItem = AlertDialog.Builder(this)
            .setTitle("Choose one of these options")
            .setSingleChoiceItems(options, 0) { dialogInterface, i ->
                Toast.makeText(this, "You clicked on ${options[i]}", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Accept"){ _, _ ->
                Toast.makeText(this, "You accepted the single choice dialog", Toast.LENGTH_SHORT).show()

            }
            .setNegativeButton("Decline"){ _, _ ->
                Toast.makeText(this, "You declined the single choice dialog", Toast.LENGTH_SHORT).show()
            }.create()

        binding.btnDialog2.setOnClickListener() {
            singleChoiceItem.show()
        }

        //multi choice dialog
        val multiChoiceDialog = AlertDialog.Builder(this)
            .setTitle("Choose one of these options")
            .setMultiChoiceItems(options, booleanArrayOf(false, false, false)) { _, i, isChecked ->
                if (isChecked) {
                    Toast.makeText(this, "You checked  ${options[i]}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "You unchecked  ${options[i]}", Toast.LENGTH_SHORT).show()
                }

            }
            .setPositiveButton("Accept"){ _, _ ->
                Toast.makeText(this, "You accepted the multi choice dialog", Toast.LENGTH_SHORT).show()

            }
            .setNegativeButton("Decline"){ _, _ ->
                Toast.makeText(this, "You declined the multi choice dialog", Toast.LENGTH_SHORT).show()
            }.create()

        binding.btnDialog3.setOnClickListener() {
            multiChoiceDialog.show()
        }

        //custom spinner list
        val customList = listOf("first", "second", "third", "fourth")
        val adapter = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, customList)
        binding.spMonths.adapter = adapter

        binding.spMonths.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                    Toast.makeText(this@MainActivity,
                        "You selected ${parent?.getItemAtPosition(position).toString()}",
                        Toast.LENGTH_LONG).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        //end spinner

        // Launch third activity
        binding.btnRecyclerView.setOnClickListener() {
            Intent(this, RecyclerviewActivity::class.java).also {
                startActivity(it)
            }
        }

        // Launch Fragment activity
        binding.btnLaunchFragment.setOnClickListener() {
            Intent(this, FragmentActivity::class.java).also {
                startActivity(it)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
//        return super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.miSettings -> Toast.makeText(this, "You clicked on Settings", Toast.LENGTH_SHORT).show()
            R.id.miAddContact -> Toast.makeText(this, "You clicked on Add Contact", Toast.LENGTH_SHORT).show()
            R.id.miFavorites -> Toast.makeText(this, "You clicked on Favorites", Toast.LENGTH_SHORT).show()
            R.id.miFeedback -> Toast.makeText(this, "You clicked on Feedback", Toast.LENGTH_SHORT).show()
            R.id.miClose -> finish()
        }
        return super.onOptionsItemSelected(item)
    }



    // request permissions
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)

    private fun hasWriteExternalStoragePermission() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED) == PackageManager.PERMISSION_GRANTED

    private fun hasLocationForegroundPermission() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun hasLocationBackgroundPermission() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun requestPermissions() {
        var permissionsToRequest = mutableListOf<String>()
        if (!hasWriteExternalStoragePermission()) {
            permissionsToRequest.add(Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED)
        }
        if (!hasLocationForegroundPermission()) {
            permissionsToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!hasLocationBackgroundPermission()) {
            permissionsToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("PermissionsRequest", "${permissions[i]} granted.")
                }
            }
        }
    }

}