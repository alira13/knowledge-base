package com.example.multithreading.coroutines

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
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class CoroutinesMainActivity : AppCompatActivity() {

    private lateinit var loadBtn: Button
    private lateinit var contentTv: TextView
    private lateinit var timerTv: TextView

    private val myScope = CoroutineScope(CoroutineName("My coroutine"))

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
            // создаем корутину и запускаем ее
            // все длительные операции у нас в suspend-функциях, которые могут приостанавливаться
            // и затем возобновляться чтобы не блокировать поток
            // запускаем корутину внутри myScope чтобы можно было его потом отменить=отменить все корутины
            myScope.launch {
                val book = loadBook()
                Log.d("MY", "Get book $book")
                contentTv.append("Loading book...\n\n")
                contentTv.append(book.toString() + "\n\n")

                contentTv.append("Loading author...\n\n")
                val author = loadAuthor()
                Log.d("MY", "Get author $author")
                contentTv.append(author.toString() + "\n\n")
                contentTv.append("Finish loading...\n\n")
                // Нам нужно запустить в том же потоке, что и изменил первоначальное значение
                // это был Main(Ui-thread), поэтому мы и запускаем внутри него
                runOnUiThread {
                    loadBtn.isEnabled = true
                }
            }
        }
    }


    // весь код по умолчанию выполняется в главном потоке,
    // если мы не положим его в отельный поток, то этот код заблокирует главный поток
    // то есть здесь будут происходить расчеты, но на UI не будет изменений
    // тут пока оставили внутри thread реализацию
    private fun startTimer() {
        thread {
            var totalSeconds = 0
            while (true) {
                val minutes = totalSeconds / 60
                val seconds = totalSeconds % 60
                timerTv.text = String.format("%02d:%02d", minutes, seconds)
                Thread.sleep(1000)
                totalSeconds++
            }
        }
    }

    // долгие операции выносят в отельный поток
    // вместо механизма callback используем suspend функцию
    // она запустит delay, освободит ресурсы и даст возможность другим функциям стартовать и выполняться
    // как только delay закончит работу, функция опять возобновиться и вернет Book
    private suspend fun loadBook(): Book {
        delay(3000)
        return (Book("1984", 1949, "Dystopia"))
    }

    private suspend fun loadAuthor(): Author {
        delay(3000)
        return (Author("Oruel", "British writer"))
    }

    // отмена scope
    // отменяет все корутины внутри scope
    override fun onDestroy() {
        super.onDestroy()
        myScope.cancel()
    }
}