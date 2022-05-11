package com.anhandra.happyplaces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.anhandra.happyplaces.databinding.ActivityBaseBinding
import com.google.android.material.navigation.NavigationView

class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityBaseBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout = binding.drawerLayout
        val navController = this.findNavController(R.id.myNavHostFragment)


        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
        binding.navView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.shareButton) {
            shareApp()
        }
        if (item.itemId == R.id.aboutFragment) {
            this.findNavController(R.id.myNavHostFragment).navigate(R.id.aboutFragment)
            drawerLayout.closeDrawer(Gravity.LEFT)
        }
        if (item.itemId == R.id.contactFragment) {
            this.findNavController(R.id.myNavHostFragment).navigate(R.id.contactFragment)
            drawerLayout.closeDrawer(Gravity.LEFT)
        }
        if (item.itemId == R.id.listFragment) {
            this.findNavController(R.id.myNavHostFragment).navigate(R.id.listFragment)
            drawerLayout.closeDrawer(Gravity.LEFT)
        }
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    private fun getShareIntent(): Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text))
        return shareIntent
    }

    private fun shareApp() {
        startActivity(getShareIntent())
    }


}