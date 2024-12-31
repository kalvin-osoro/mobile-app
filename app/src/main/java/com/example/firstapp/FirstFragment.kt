package com.example.firstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.firstapp.databinding.FragmentFirstBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_first, container, false)
        _binding = FragmentFirstBinding.inflate(inflater)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val images = listOf(
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4
        )

        val adapter = ViewpagerAdapter(images)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"

        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(requireContext(), "Selected ${tab?.text}", Toast.LENGTH_SHORT).show()

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(requireContext(), "Unselected ${tab?.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(requireContext(), "Reselected ${tab?.text}", Toast.LENGTH_SHORT).show()
            }
        })

//        binding.viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
//
//        binding.viewPager.beginFakeDrag()
//        binding.viewPager.fakeDragBy(-10f)
//        binding.viewPager.endFakeDrag()



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}