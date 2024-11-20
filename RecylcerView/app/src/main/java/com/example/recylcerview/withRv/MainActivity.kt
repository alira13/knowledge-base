package com.example.recylcerview.withRv

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.recylcerview.R
import com.example.recylcerview.withRv.ListAdapter.Companion.VIEW_LAYOUT_TYPE_1_POOL_SIZE
import com.example.recylcerview.withRv.ListAdapter.Companion.VIEW_LAYOUT_TYPE_2_POOL_SIZE

class MainActivity : AppCompatActivity() {

    private lateinit var listAdapter: ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_with_rv)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val rv = findViewById<RecyclerView>(R.id.rv_list)
        with(rv){
            listAdapter = ListAdapter()
            adapter = listAdapter
            recycledViewPool.setMaxRecycledViews(R.layout.view_item, VIEW_LAYOUT_TYPE_1_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(R.layout.view_item_modified, VIEW_LAYOUT_TYPE_2_POOL_SIZE)
        }
    }
}