package com.example.recylcerview.withRvOpt

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recylcerview.R

class ShopListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // Решает проблема 2: поиск view для присвоения текста.
    // очень медленный. Но нужно вызвать findViewById для каждого элемента списка
    // а если нужно присвоить не только текст, но и еще поля?
    // Вызовется только 20 раз
    val textView: TextView = itemView.findViewById<TextView>(R.id.tv_item)
}