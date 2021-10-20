package com.app.shourtcuttask.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.app.shourtcuttask.R
import com.app.shourtcuttask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        val navController =
            (supportFragmentManager.findFragmentById(R.id.comic_nav_host_fragment) as NavHostFragment).navController
        activityMainBinding.comicBottomNavigationView.setupWithNavController(navController)
        activityMainBinding.comicBottomNavigationView.setOnItemReselectedListener {
        }
    }
}