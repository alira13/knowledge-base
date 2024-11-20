package com.example.recylcerview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var llList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        llList = findViewById<LinearLayout>(R.id.ll_list)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        showList()

    }

    private fun showList() {
        for (i in 0..1000) {
            val view = LayoutInflater.from(this).inflate(R.layout.view_item, llList, false)
            val textView = view.findViewById<TextView>(R.id.tv_item)
            textView.text = "Hi $i"
            llList.addView(view)
            view.setOnLongClickListener{
                textView.text="Hello $i"
                true
            }
        }
    }
}