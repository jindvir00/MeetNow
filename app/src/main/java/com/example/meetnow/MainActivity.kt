package com.example.meetnow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
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

        binding.authenticate.setOnClickListener {
            startActivity(Intent(this@MainActivity , AuthenticationActivity::class.java))
            finish()
        }
    }
}