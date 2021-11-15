package com.wiktor.udemyprorazdel9servicces

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wiktor.udemyprorazdel9servicces.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}