package com.example.multithreading.suspendImpl

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.multithreading.R
import com.example.multithreading.entities.Author
import com.example.multithreading.entities.Book
import kotlin.concurrent.thread

class SuspendImplMainActivity : AppCompatActivity() {

    private lateinit var loadBtn: Button
    private lateinit var contentTv: TextView
    private lateinit var timerTv: TextView

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

        // callback hell
        loadBtn.setOnClickListener {
            loadData()
        }
    }

    // сколько раз нужно выйти из loadData и вернуться снова? Точки останова по сути
    private fun loadData(state: Int = 0, data: Any? = null) {
        when (state) {
            0 -> {
                // выполняем действия последовательно
                // старт
                loadBtn.isEnabled = false
                contentTv.append("Loading book...\n\n")
                // запускаем метод и выходим из него, а когда он закончит выполнение, стартуем следующий шаг
                loadBook { book: Book -> loadData(1, book) }
                // отпустили выполнение
            }

            1 -> {
                // старт
                val book = data as Book
                contentTv.append(book.toString() + "\n\n")
                contentTv.append("Loading author...\n\n")
                // it
                loadAuthor { author: Author -> loadData(2, author) }
                // отпустили выполнение
            }

            2 -> {
                // старт
                val author = data as Author
                contentTv.append(author.toString() + "\n\n")
                contentTv.append("Finish loading...\n\n")
                runOnUiThread {
                    loadBtn.isEnabled = true
                }
            }
        }
    }

    // весь код по умолчанию выполняется в главном потоке,
    // если мы не положим его в отельный поток, то этот код заблокирует главный поток
    // то есть здесь будут происходить расчеты, но на UI не будет изменений
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
    // если нужно чтобы операция вернула даные по завершению, используют механизм callback
    // вызов функции происходит не сразу,а в будущем после окончания метода sleep

    // как только загрузится книжка, выполни какую-то фунцию с именем callback с аргументами
    private fun loadBook(callback: (Book) -> Unit) {
        thread {
            Thread.sleep(3000)
            callback(Book("1984", 1949, "Dystopia"))
        }
    }

    private fun loadAuthor(callback: (Author) -> Unit) {
        thread {
            Thread.sleep(3000)
            callback(Author("Oruel", "British writer"))
        }
    }
}