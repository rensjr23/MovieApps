package com.example.movieapps.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movieapps.R
import com.example.movieapps.databinding.HomeMainActivityBinding
import com.example.movieapps.presentation.view.fragment.HomeFragment
import com.example.movieapps.presentation.view.fragment.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeMainActivity : AppCompatActivity() {
    private lateinit var binding: HomeMainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.setOnNavigationItemSelectedListener(onNavigateItemSelectedListener)
        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId =
                R.id.navigationHome
        }
    }

    private val onNavigateItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {item ->
        when(item.itemId){
            R.id.navigationHome->{
                replaceFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigationSearch->{
                replaceFragment(SearchFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
            false
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmenContainer, fragment)
            .commit()
    }
}