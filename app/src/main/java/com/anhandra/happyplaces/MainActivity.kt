package com.anhandra.happyplaces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import com.anhandra.happyplaces.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.splashScreen.setImageResource(R.drawable.mountain_view)

        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({ startBaseActivity() }, 3000)


    }

    private fun startBaseActivity() {
        val intent = Intent(this, BaseActivity::class.java)
        startActivity(intent)
        finish()
    }
}