package com.example.recylcerview.withRv

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recylcerview.R
import com.example.recylcerview.withRv.ListAdapter.Companion.VIEW_LAYOUT_TYPE_1_POOL_SIZE
import com.example.recylcerview.withRv.ListAdapter.Companion.VIEW_LAYOUT_TYPE_2_POOL_SIZE

class MainActivity : AppCompatActivity() {

    private lateinit var listAdapter: ListAdapter
    private var items: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_with_rv)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        for (i in 0..100) {
            items.add(i, "$i")
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val rv = findViewById<RecyclerView>(R.id.rv_list)
        with(rv){
            listAdapter = ListAdapter()
            listAdapter.items = items
            adapter = listAdapter
            recycledViewPool.setMaxRecycledViews(R.layout.view_item, VIEW_LAYOUT_TYPE_1_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(R.layout.view_item_modified, VIEW_LAYOUT_TYPE_2_POOL_SIZE)
            setupLongClickListener()
        }
        setupSwipeListener(rv)
    }

    private fun setupLongClickListener() {
        listAdapter.onLongClick = {
            Log.d("MYMY", "${it}!!!")
        }
    }

    private fun setupSwipeListener(rv: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = items[viewHolder.adapterPosition]
                Log.d("MYMY", "Swipe ${item}")
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rv)
    }
}