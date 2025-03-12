package com.example.flowandchannels.stateAsFlow

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
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var searchButton: Button
    private lateinit var searchRequest: EditText
    private lateinit var searchResult: TextView

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val repository = Repository

    // запросы пользователя в виде канала, чтобы из разных корутин могли сыпаться запросы
    private var requests = Channel<String> { }

    // состояние интерфейса
    private var screenState = MutableSharedFlow<ScreenState>()


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
        // корутина-создатель данных
        scope.launch {
            requests.send(searchRequest.text.trim().toString())
        }
    }

    init {
        // корутина-потребитель данных. Подписываемся
        // и в самом начале подписки на старте Activity мы устанавливаем InitalState
        // затем на каждом поисковом запросе мы меняем state Loading-Delay(500)-Loaded or Not found
        requests.consumeAsFlow()
            .onStart { screenState.emit(ScreenState.Initial) }
            // делаем какое-то действие для каждого элемента flow
            .onEach {
                screenState.emit(ScreenState.Loading)
            }
            .debounce(500)
            .map {
                if (it.isEmpty()) screenState.emit(ScreenState.Initial)
                else {
                    val response = repository.loadDefinition(it)

                    if (response.isEmpty())
                        screenState.emit(ScreenState.NotFound)
                    else
                        screenState.emit(ScreenState.DefinitionsLoaded(response))
                }
            }
            .launchIn(scope)

        // flow отвечает за изменение UI в зависимости от state
        screenState.onEach {
            when (it) {
                is ScreenState.DefinitionsLoaded -> {
                    searchResult.text = it.definition.joinToString("\n\n")
                    searchButton.isEnabled = true
                }

                ScreenState.Initial -> {
                    searchResult.text = ""
                    searchButton.isEnabled = false
                }

                ScreenState.Loading -> {
                    searchResult.text = "Loading..."
                    searchButton.isEnabled = false
                }

                ScreenState.NotFound -> {
                    searchResult.text = "Not found"
                    searchButton.isEnabled = true
                }
            }
        }.launchIn(scope)
    }

    private fun initViews() {
        searchButton = findViewById(R.id.btn_search)
        searchRequest = findViewById(R.id.et_search_request)
        searchResult = findViewById(R.id.tv_search_result)
    }
}