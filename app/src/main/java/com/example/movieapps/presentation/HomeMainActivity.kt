package com.example.movieapps.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapps.R
import com.example.movieapps.databinding.HomeMainActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView

class HomeMainActivity : AppCompatActivity() {
    private lateinit var binding: HomeMainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.setOnNavigationItemSelectedListener(onNavigateItemSelectedListener)
    }

    private val onNavigateItemSelectedListener =
        BottomNavigationItemView.on
}