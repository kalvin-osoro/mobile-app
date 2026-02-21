package com.example.firstapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.firstapp.ui.ThirdFragmentScreen
import com.example.firstapp.ui.theme.FirstAppTheme

class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FirstAppTheme {
                    ThirdFragmentScreen(
                        onSave = { name, age, isAdult ->
                            val sharedPref = activity?.getSharedPreferences("myPref", Context.MODE_PRIVATE)
                            sharedPref?.edit()?.apply {
                                putString("name", name)
                                putInt("age", age)
                                putBoolean("isAdult", isAdult)
                                apply()
                            }
                        },
                        onLoad = {
                            val sharedPref = activity?.getSharedPreferences("myPref", Context.MODE_PRIVATE)
                            val name = sharedPref?.getString("name", null)
                            val age = sharedPref?.getInt("age", 0)
                            val isAdult = sharedPref?.getBoolean("isAdult", false) ?: false
                            Triple(name, age, isAdult)
                        }
                    )
                }
            }
        }
    }
}
