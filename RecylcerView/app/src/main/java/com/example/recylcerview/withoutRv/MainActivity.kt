package com.example.recylcerview.withoutRv

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recylcerview.R

class MainActivity : AppCompatActivity() {

    private lateinit var llList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_without_rv)

        llList = findViewById<LinearLayout>(R.id.ll_list)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        showList()

    }

    private fun showList() {
        // проблема 3: если изменился 1 элемент списка,. то нужно перерисовать все 10000 элементов
        for (i in 0..1000) {
            // проблема 1: создание view. ресурсозатратный, но вызвывается каждого элемента списка
            val view = LayoutInflater.from(this).inflate(R.layout.view_item, llList, false)
            // проблема 1: поиск view для присвоения текста.
            // очень медленный. Но нужно вызвать findViewById для каждого элемента списка
            // а если нужно присвоить не только текст, но и еще поля?
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