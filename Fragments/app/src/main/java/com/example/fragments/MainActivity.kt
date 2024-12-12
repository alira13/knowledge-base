package com.example.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchFragment()
    }

    private fun launchFragment() {
        val fragment =
            PrepareFragment.newInstance("PrepareFragment", "launch")
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_fragment_container, fragment)
            .commit()
    }
}