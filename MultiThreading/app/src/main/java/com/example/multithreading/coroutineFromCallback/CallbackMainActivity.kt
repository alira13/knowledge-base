package com.example.multithreading.coroutineFromCallback

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.multithreading.R
import com.example.multithreading.entities.Author
import com.example.multithreading.entities.Book
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.concurrent.thread

class CoroutineCallbackMainActivity : AppCompatActivity() {

    private lateinit var loadBtn: Button
    private lateinit var contentTv: TextView
    private lateinit var timerTv: TextView


    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadBtn = findViewById(R.id.btn_load_button)
        contentTv = findViewById(R.id.tv_content)
        timerTv = findViewById(R.id.tv_timer)

        startTimer()

        loadBtn.setOnClickListener {
            loadBtn.isEnabled = false

            val job = scope.launch {
                try {
                    val book = loadBook()
                    contentTv.append("Loading book...\n\n")
                    contentTv.append(book.toString() + "\n\n")

                    contentTv.append("Loading author...\n\n")
                    val author = loadAuthor()
                    contentTv.append(author.toString() + "\n\n")

                    contentTv.append("Finish loading...\n\n")
                } catch (e: CancellationException) {
                    Log.d("MY", "CancellationException")
                }
                runOnUiThread {
                    loadBtn.isEnabled = true
                }
            }

            /*
            scope.launch {
                Thread.sleep(2000)
                job.cancel()
            }
             */
        }
    }

    // весь код по умолчанию выполняется в главном потоке,
    // если мы не положим его в отельный поток, то этот код заблокирует главный поток
    // то есть здесь будут происходить расчеты, но на UI не будет изменений
    private fun startTimer() {
        thread {
            var totalSeconds = 0
            while (true) {
                Log.d("MY", totalSeconds.toString())
                val minutes = totalSeconds / 60
                val seconds = totalSeconds % 60
                timerTv.text = String.format("%02d:%02d", minutes, seconds)
                Thread.sleep(1000)
                totalSeconds++
            }
        }
    }

    // допустим у нас есть библиотечная функция с callnback когда корутины еще не были изобретены
    // а мы хотим ее уже использовать внутри корутины
    // 1. вызвать suspendCoroutine c параметром типа который хотим возвратить Book
    // 2. это лямбда с входным Continuation - объектом который используется в state-машине
    // 3. как только данные загружены - надо сообщеить об этом continuation resumeWith


    private suspend fun loadBook(): Book {
        return suspendCancellableCoroutine<Book> { continuation ->
            loadBook { book ->
                try {
                    scope.ensureActive()
                    continuation.resumeWith(Result.success(book))
                } catch (c: CancellationException) {
                    Log.d("MY", "Catch IsActive = ${scope.isActive}")
                    throw c
                } catch (e: Exception) {
                    Log.d("MY", "Catch IsActive = ${scope.isActive}")
                    continuation.resumeWith(Result.failure(Exception("При загрузке книги что-то пошло не так")))
                }
            }
        }
    }

    private fun loadBook(callback: (Book) -> Unit) {
        thread {
            Log.d("MY", "Start loading book = ${scope.isActive}")
            Thread.sleep(3000)
            callback(Book("1984", 1949, "Dystopia"))
        }
    }

    private suspend fun loadAuthor(): Author {
        return suspendCancellableCoroutine { continuation ->
            loadAuthor { author ->
                continuation.resumeWith(
                    try {
                        scope.ensureActive()
                        Result.success(author)
                    } catch (c: CancellationException) {
                        Log.d("MY", "Прерывание загрузки автора")
                        throw c
                    } catch (e: Exception) {
                        Result.failure(Exception("При загрузке книги что-то пошло не так"))
                    }
                )
            }
        }
    }

    private fun loadAuthor(callback: (Author) -> Unit) {
        thread {
            Thread.sleep(3000)
            callback(Author("Oruel", "British writer"))
        }
    }
}