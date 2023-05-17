package com.example.meetnow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.meetnow.databinding.ActivityMainBinding
import com.example.meetnow.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.authenticate.setOnClickListener {
//            startActivity(Intent(this@MainActivity , AuthenticationActivity::class.java))
//            finish()
//        }

        loadFragment(HomeFragment())
        binding.settingsImg.setOnClickListener {
            loadFragment(SettingsFragment())
        }
        binding.itemBottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    loadFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.notifications -> {
                    loadFragment(VideoOptionsFragment())
                    return@setOnItemSelectedListener true
                }

                else -> {
                    return@setOnItemSelectedListener false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.ll_fragment_view ,fragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.commit()

//        binding.itemBottomNav.selectedItemId = index
    }
}