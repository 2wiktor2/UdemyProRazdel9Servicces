package com.wiktor.udemyprorazdel9servicces

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.wiktor.udemyprorazdel9servicces.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.i("qwertyu", "onCreate")

        binding.simpleService.setOnClickListener {
            Log.i("qwertyu", "Click")
            startService(MyService2.newIntent(this, 25))
        }
    }
}