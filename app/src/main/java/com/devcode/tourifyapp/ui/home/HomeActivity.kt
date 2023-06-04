package com.devcode.tourifyapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}