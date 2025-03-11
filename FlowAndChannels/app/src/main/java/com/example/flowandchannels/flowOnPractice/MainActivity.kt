package com.example.flowandchannels.flowOnPractice

import android.os.Bundle
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var searchButton: Button
    private lateinit var searchRequest: EditText
    private lateinit var searchResult: TextView

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val repository = Repository

    private var job: Job? = null

    // запросы пользователя
    private var requests = Channel<String> { }

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
            requests.send(searchRequest.text.trim().toString())
        }
    }

    init {
        requests.consumeAsFlow()
            // делаем какое-то действие для каждого элемента flow
            .onEach {
                searchButton.isEnabled = false
                searchResult.text = "Loading..."
                searchRequest.text.trim().toString()
            }
            // ожидание 500, если поступает новый в течении 500
            // то старый отменяется и новый побежить по цепочке
            .debounce(500)
            // преобразует один тип в другой на основе лямбды
            .map {
                repository.loadDefinition(it)
            }
            // преобразует один тип в другой на основе лямбды
            .map {
                it.joinToString("\n\n").ifEmpty { "Not found" }
            }.onEach {
                searchResult.text = it
                searchButton.isEnabled = true
            }
            // создает корутину на scope и вызывает collect на flow
            .launchIn(scope)
    }

    private fun initViews() {
        searchButton = findViewById(R.id.btn_search)
        searchRequest = findViewById(R.id.et_search_request)
        searchResult = findViewById(R.id.tv_search_result)
    }
}