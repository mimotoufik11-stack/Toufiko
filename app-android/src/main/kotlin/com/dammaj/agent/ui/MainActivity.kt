package com.dammaj.agent.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dammaj.agent.R
import com.dammaj.agent.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        binding.textWelcome.text = getString(R.string.welcome_message)
    }
}
