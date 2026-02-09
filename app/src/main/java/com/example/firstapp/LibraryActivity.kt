package com.example.firstapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.data.LibraryItem
import com.example.firstapp.databinding.ActivityLibraryBinding
import com.google.android.material.tabs.TabLayout

class LibraryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLibraryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf(
            LibraryItem("Chill Vibes", "Playlist • 24 songs", "https://lh3.googleusercontent.com/aida-public/AB6AXuAPdTsiPZKoyLsiOuZsrk4ed_2BuYE8jwvNkXNbHJVr8w3VasDKKKXvnt6JAFeCBpYTwDk5vaxgcrt1zCWLyAd4K2PxEyd6trN8sekOzvHhZh5a6RbIkrq8riCxJsBwwaVdbAGH0OFa3yj86C767s_ffJCQHGIuHW-LQyzCkMkg67T00Z_vQrdUO4C99d0BlHxHK7DOnUSH-pWVMp61HfkFkN33ER9_QsdBUUAHVgFiTNLklFG7SjomOf3LbDEpdxvb_rsUIvgylIo"),
            LibraryItem("Ethan Carter", "Artist", "https://lh3.googleusercontent.com/aida-public/AB6AXuCU8NiBOqTmpaiPTh2xOAhgoevLcyrYiIKbxZKxHtnCfyxta--iF_t6sKKPKCAykw1lEqgLi_6odPtamtkaJNfmbbLAss4GhKzCCwoT6rGwg4BAq684L3H_lwZqVAXPVDeHbxWk49Eij4UlSIPW4GJBu19wFjOl1XOCYbewahXFqWmS6plTZaaqfyyhmUiZFZLRVQJHiRydYMab8M-6JvqEqY2Qlbvhoax8ebwL1R1NEIqYKif9padFqtjJugqk67Ep6Ht5wIYYGdg", true),
            LibraryItem("Midnight Echoes", "Album", "https://lh3.googleusercontent.com/aida-public/AB6AXuB0mrxX9aE0XQqf7d80x5KP9fcSuvQi9a3MLNFHpYkRIoJHq3BlX1CyHPp9COwhg36Lzb1das26D2DD7F39XSSWDs4EjAp4kPkB7Ax5cLVtFCA17pwCTccWVBwWZaioVHgX1NnFl8Vx_9Anjn8NZWdUMGZ3dyOjlnBjxSdOcS_ArSoGNBKE7zE2BuApJa_lKNYWkmuGpsSBAZvNBE16DLRi3t_GDOwcLNEnEFu9J6fgKUeBusE_CT5cx9R1z2vwDWCcCSGoIJtZ1FY"),
            LibraryItem("Road Trip Mix", "Playlist • 35 songs", "https://lh3.googleusercontent.com/aida-public/AB6AXuBDjMgM4CxUojL0Tj9Iu3ss5vh9WYY8A87ADqwYEd626UygreWghzS7kpkGyXE9SzIRjWe78hX4KNDEpKCCBu4rWBGMmHB65ttYYJ5zsORJVrarX8N-9IQr15FQBcNxvLTsj5K_ToHoURzV71BDckyMpsl0SdpW0m9f1XDxf9V3jbl4hXxfZ_A-99j1tu3pWLavYmxOmuQfyPrCPrHINnVVQVteJduT7B0oU7MbPRwScNivN-Wr-eAF4voDzTbizoAcWPAE-TKfHXU"),
            LibraryItem("Olivia Hayes", "Artist", "https://lh3.googleusercontent.com/aida-public/AB6AXuBsS3ReebwM1kwTVVl62JXOK71FaoDiRVifes03VEa9CF0fmK-NKTTvUwCfC8H3yqd003u0E0qr8cNf98YNt6-gdxDo6mNyLQ8IkVfEzLGtsTaKyrZsayXka07l2Xiu_roxmwmzQWzwhddo2URxsq31W3q4mcv_yiM69EcUk6E_FzTFvtBrNuT6HBVL8aQ5kcfd9MDBxuCdquLIOWWr6VRZwZyzB512BCcyZQCRkM4JY2nB0XFszkSK0ebCIq9aD4ptdqLTaRZmldI", true),
            LibraryItem("Urban Dreams", "Album", "https://lh3.googleusercontent.com/aida-public/AB6AXuCgYLLMhh8r8CkHYqZoiCD_8qvEysfigybLxJNDSX1GNgQqgPa7ew43D92e5KyioidRvShGv0E8mYGHntZXf1Dq3-eWyez4t7axn_7duf-Wi_N8yZ2RXzA9u0awAL9NcEAl1qgby4bWJ4KbKW1XZ2wfzaS4ta2ltGinsEfolfyPOKn29UXGDIs_Px5GNYkya1xqG9yhWxOhpEfpBz2PXy9Lmx-poheFF_jpxUBc3hcc64w4G6uGdNhxeoUCRWw8g2IESlzzA6uKfkQ")
        )

        val adapter = LibraryAdapter(items)
        binding.rvLibrary.layoutManager = LinearLayoutManager(this)
        binding.rvLibrary.adapter = adapter

        binding.bottomNav.selectedItemId = R.id.nav_library

        // Listeners for Tabs
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(this@LibraryActivity, "Selected: ${tab?.text}", Toast.LENGTH_SHORT).show()
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // Listeners for Bottom Navigation
        binding.bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_home -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                R.id.nav_search -> Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
                R.id.nav_library -> Toast.makeText(this, "Library", Toast.LENGTH_SHORT).show()
            }
            true
        }

        binding.fabAdd.setOnClickListener {
            Toast.makeText(this, "Add new item", Toast.LENGTH_SHORT).show()
        }
    }
}
