package com.example.firstapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.firstapp.ui.FragmentActivityScreen
import com.example.firstapp.ui.theme.FirstAppTheme

class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val thirdFragment = ThirdFragment()

        setContent {
            FirstAppTheme {
                FragmentActivityScreen(
                    onHomeClick = { setCurrentFragment(firstFragment) },
                    onMessagesClick = { setCurrentFragment(secondFragment) },
                    onProfileClick = { setCurrentFragment(thirdFragment) }
                )
            }
        }

        // Initial fragment
        if (savedInstanceState == null) {
            if (intent.getStringExtra("navigate_to") == "SecondFragment") {
                setCurrentFragment(secondFragment)
            } else {
                setCurrentFragment(firstFragment)
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            addToBackStack(null)
            commit()
        }
    }
}
