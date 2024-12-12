package com.example.fragments

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.fragments.ReadyFragment.StatusChangedListener

class MainActivity : AppCompatActivity(), StatusChangedListener {

    private var newFragmentContainer: FragmentContainerView? = null
    private lateinit var statusTextView: TextView

    private var status: String = "Preparing..."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("onCreate", "MainActivity")
        newFragmentContainer = findViewById(R.id.new_fragment_container)

        // при перевороте экрана система сама создает фрагмент,
        // поэтому нам нужно создать фрагменты только при первом запуске activity
        launchFragments()

        statusTextView = findViewById(R.id.tv_status)
        Log.d("OnCreate", "status = $status")
        statusTextView.text = this.status
    }

    private fun launchFragments() {
        Log.d("onCreate", "----launchFragments")
        addPrepareFragment()
        addReadyFragment()
    }

    private fun isLandscapeOrientation(): Boolean {
        return newFragmentContainer != null
    }

    private fun addReadyFragment() {
        if (isLandscapeOrientation()) {
            val readyFragment = ReadyFragment()
            // add всегда создает новый фрагмент,
            // можно использовать replace, чтобы заменять старый фрагмент на новый
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.new_fragment_container, readyFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun addPrepareFragment() {
        val fragment =
            PrepareFragment.newInstance("PrepareFragment", "launch")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onStatusChanged(status: String) {
        this.status = status
        statusTextView.text = this.status
    }
}