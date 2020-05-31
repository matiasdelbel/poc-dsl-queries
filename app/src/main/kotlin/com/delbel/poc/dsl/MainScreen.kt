package com.delbel.poc.dsl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.delbel.poc.dsl.databinding.ScreenMainBinding

class MainScreen : AppCompatActivity() {

    private lateinit var screenBinding: ScreenMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        screenBinding = ScreenMainBinding.inflate(layoutInflater)
        setContentView(screenBinding.root)
    }
}