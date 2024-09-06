package com.example.steamgameexplorer

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.example.steamgameexplorer.databinding.ActivityMainBinding
import com.example.steamgameexplorer.ui.home.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Set the user preferred theme before anything else is done

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyUserPreferredTheme()

        setupNavigation()
        setupViewModel()
    }

    private fun applyUserPreferredTheme() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { pref, key ->
            Log.d("MainActivity", pref.getString(key, "").toString())
            when (pref.getString(key, "").toString()) {
                "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
        prefs.registerOnSharedPreferenceChangeListener(listener)
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_category, R.id.navigation_favorites, R.id.navigation_settings
        ))

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // Observe ViewModel data and update UI accordingly
        viewModel.games.observe(this, { games ->
            // Implement UI update logic here
        })

        // Load initial data
        viewModel.loadFeaturedGames()
    }
}
