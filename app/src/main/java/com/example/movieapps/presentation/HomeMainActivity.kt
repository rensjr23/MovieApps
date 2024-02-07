package com.example.movieapps.presentation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.movieapps.databinding.HomeMainActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.movieapps.R
import com.example.movieapps.presentation.view.fragment.HomeFragment
import com.example.movieapps.presentation.view.fragment.MovieDetailsFragment
import com.example.movieapps.presentation.view.fragment.MovieFragment
import com.example.movieapps.presentation.view.fragment.SearchFragment
import com.example.movieapps.presentation.view.fragment.VideoFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeMainActivity : AppCompatActivity() {
    private lateinit var binding: HomeMainActivityBinding
    private lateinit var navController: NavController
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            reloadCurrentFragmentData()
        }

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    private fun reloadCurrentFragmentData() {
        val activeFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.childFragmentManager?.fragments?.get(0)

        when (activeFragment) {
            is HomeFragment -> {
                activeFragment.reloadData()
            }
            is MovieFragment -> {
                activeFragment.reloadData()
            }
            is SearchFragment -> {
                activeFragment.reloadData()
            }
            is VideoFragment -> {
                activeFragment.reloadData()
            }
            is MovieDetailsFragment -> {
                activeFragment.reloadData()
            }
        }
        swipeRefreshLayout.isRefreshing = false
    }
}
