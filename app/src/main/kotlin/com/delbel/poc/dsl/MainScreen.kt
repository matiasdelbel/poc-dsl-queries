package com.delbel.poc.dsl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.delbel.poc.dsl.databinding.ScreenMainBinding

class MainScreen : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var screenBinding: ScreenMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        screenBinding = ScreenMainBinding.inflate(layoutInflater)
        setContentView(screenBinding.root)

        navController = findNavController(R.id.navigation_host)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            screenBinding.toolbar.isGone = OWN_TOOLBAR_DESTINATIONS.contains(destination.id)
            if (screenBinding.toolbar.isVisible) setUpToolbar()
        }
    }

    private fun setUpToolbar() {
        val toolbarConfig = AppBarConfiguration(topLevelDestinationIds = setOf(R.id.people_screen))

        setSupportActionBar(screenBinding.toolbar)
        screenBinding.toolbar.setupWithNavController(navController, toolbarConfig)
    }

    companion object {
        private val OWN_TOOLBAR_DESTINATIONS = setOf(R.id.people_screen)
    }
}