package com.example.cahptertujuhlatihankelompokdua.view

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.cahptertujuhlatihankelompokdua.R
import android.os.Bundle
import android.view.View
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val navHost= supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHost.navController
        bottomnav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment ) {
                bottomnav.visibility = View.VISIBLE
            } else if (destination.id == R.id.favoriteFragment ){
                bottomnav.visibility = View.VISIBLE
            }
            else {
                bottomnav.visibility = View.GONE
            }


        }
    }
}