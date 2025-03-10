package com.example.flowandchannels.flowOnPractice

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.example.flowandchannels.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var searchButton: Button
    private lateinit var searchRequest: EditText
    private lateinit var searchResult: TextView

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val repository = Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()

        searchButton.setOnClickListener {
            loadDefinitions()
        }

        searchRequest.doOnTextChanged { _, _, _, _ ->
            loadDefinitions()
        }
    }

    private fun loadDefinitions() {
        scope.launch {
            searchButton.isEnabled = false
            searchResult.text = "Loading..."
            val word = searchRequest.text.trim().toString()
            Log.d("MY", "$word")
            val result = repository.loadDefinition(word).joinToString("\n\n")
            Log.d("MY", "$result")
            searchResult.text = result.ifEmpty { "Not found" }
            searchButton.isEnabled = true
        }
    }

    private fun initViews() {
        searchButton = findViewById(R.id.btn_search)
        searchRequest = findViewById(R.id.et_search_request)
        searchResult = findViewById(R.id.tv_search_result)
    }
}