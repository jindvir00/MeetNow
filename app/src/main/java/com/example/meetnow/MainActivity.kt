package com.example.meetnow

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.meetnow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sharedPrefFile = "userSignedDetail"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.itemBottomNav.selectedItemId = R.id.home
        loadFragment(HomeFragment())
        binding.settingsImg.setOnClickListener {
            loadFragment(SettingsFragment())
        }
        binding.itemBottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    loadFragment(HomeFragment())
//                    binding.itemBottomNav.selectedItemId = R.id.home
                    return@setOnItemSelectedListener true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
//                    binding.itemBottomNav.selectedItemId = R.id.home
                    return@setOnItemSelectedListener true
                }
                R.id.notifications -> {
                    loadFragment(ProfileFragment())
                    return@setOnItemSelectedListener true
                }

                else -> {
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.ll_fragment_view ,fragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//        transaction.addToBackStack(null)
        transaction.commit()

       //        binding.itemBottomNav.selectedItemId = index
    }

}