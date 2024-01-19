package com.moni.scoreapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moni.scoreapp.R
import com.moni.scoreapp.databinding.ActivityHomeBinding
import com.moni.scoreapp.databinding.CustomToolbarBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var toolbarBinding: CustomToolbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        toolbarBinding = CustomToolbarBinding.bind(binding.root)
        setContentView(binding.root)
        setSupportActionBar(toolbarBinding.topAppBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val scorerFragment = ScorerFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, scorerFragment).commit()
    }
}