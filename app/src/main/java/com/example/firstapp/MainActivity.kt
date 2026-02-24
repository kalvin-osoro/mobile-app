package com.example.firstapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.core.app.ActivityCompat
import com.example.firstapp.ui.CustomToastContent
import com.example.firstapp.ui.MainScreen
import com.example.firstapp.ui.theme.FirstAppTheme

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FirstAppTheme {
                MainScreen(
                    onApplyClick = { person ->
                        Log.d("MainActivity", "${person.firstName} ${person.lastName} born on ${person.birthDate}, from ${person.country} just applied to the formular")
                        Intent(this, SecondActivity::class.java).also {
                            it.putExtra("EXTRA_PERSON", person)
                            startActivity(it)
                        }
                    },
                    onRecyclerViewClick = {
                        Intent(this, RecyclerviewActivity::class.java).also {
                            startActivity(it)
                        }
                    },
                    onFragmentClick = {
                        Intent(this, FragmentActivity::class.java).also {
                            startActivity(it)
                        }
                    },
                    onLibraryClick = {
                        Intent(this, LibraryActivity::class.java).also {
                            startActivity(it)
                        }
                    },
                    onRequestPermissions = {
                        requestPermissions()
                    },
                    onShowDialog1 = {
                        showDialog1()
                    },
                    onShowDialog2 = {
                        showDialog2()
                    },
                    onShowDialog3 = {
                        showDialog3()
                    },
                    onShowCustomToast = {
                        showCustomToast()
                    }
                )
            }
        }
    }

    private fun showDialog1() {
        AlertDialog.Builder(this)
            .setTitle("Add Contact")
            .setMessage("Do you want to add Jane to your contacts")
            .setIcon(R.drawable.ic_add_contact)
            .setPositiveButton("yes") { _, _ ->
                Toast.makeText(this, "You added Jane to your contacts", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No") { _, _ ->
                Toast.makeText(this, "You did not add Jane to your contacts", Toast.LENGTH_SHORT).show()
            }.show()
    }

    private fun showDialog2() {
        val options = arrayOf("First item", "Second Item", "Third Item")
        AlertDialog.Builder(this)
            .setTitle("Choose one of these options")
            .setSingleChoiceItems(options, 0) { _, i ->
                Toast.makeText(this, "You clicked on ${options[i]}", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Accept") { _, _ ->
                Toast.makeText(this, "You accepted the single choice dialog", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Decline") { _, _ ->
                Toast.makeText(this, "You declined the single choice dialog", Toast.LENGTH_SHORT).show()
            }.show()
    }

    private fun showDialog3() {
        val options = arrayOf("First item", "Second Item", "Third Item")
        AlertDialog.Builder(this)
            .setTitle("Choose one of these options")
            .setMultiChoiceItems(options, booleanArrayOf(false, false, false)) { _, i, isChecked ->
                if (isChecked) {
                    Toast.makeText(this, "You checked  ${options[i]}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "You unchecked  ${options[i]}", Toast.LENGTH_SHORT).show()
                }
            }
            .setPositiveButton("Accept") { _, _ ->
                Toast.makeText(this, "You accepted the multi choice dialog", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Decline") { _, _ ->
                Toast.makeText(this, "You declined the multi choice dialog", Toast.LENGTH_SHORT).show()
            }.show()
    }

    private fun showCustomToast() {
        val composeView = ComposeView(this).apply {
            setContent {
                CustomToastContent()
            }
        }
        Toast(this).apply {
            duration = Toast.LENGTH_LONG
            setGravity(Gravity.BOTTOM, 0, 100)
            view = composeView
            show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
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
