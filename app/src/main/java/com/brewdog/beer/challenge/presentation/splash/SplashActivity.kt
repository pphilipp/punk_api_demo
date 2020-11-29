package com.brewdog.beer.challenge.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.brewdog.beer.challenge.R
import com.brewdog.beer.challenge.databinding.ActivitySplashBinding
import com.brewdog.beer.challenge.presentation.main.MainActivity

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(MainActivity.newInstance(this))
            finish()
        }, 2000)
    }
}