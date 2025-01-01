package com.example.firstapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.firstapp.databinding.FragmentFirstBinding
import com.example.firstapp.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding: FragmentSecondBinding get() = _binding!!

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_first, container, false)
        _binding = FragmentSecondBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toggle = ActionBarDrawerToggle(requireActivity(), binding.drawerLayout, R.string.open, R.string.close )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()



        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.miItem1 -> Toast.makeText(requireContext(),
                    "Clicked Item 1", Toast.LENGTH_SHORT).show()
                R.id.miItem2 -> Toast.makeText(requireContext(),
                    "Clicked Item 2", Toast.LENGTH_SHORT).show()
                R.id.miItem3 -> Toast.makeText(requireContext(),
                    "Clicked Item 3", Toast.LENGTH_SHORT).show()
            }
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}