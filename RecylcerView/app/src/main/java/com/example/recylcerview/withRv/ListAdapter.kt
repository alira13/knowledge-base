package com.example.recylcerview.withRv

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recylcerview.R

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    private var createViewHolderCount: Int = 0
    private var bindViewHolderCount: Int = 0

    var onLongClick: ((String) -> Any)? = null

    var items: MutableList<String> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    // Определяет как создать view(вызовется примерно 20(10 на экране + по 5 с запасом))
    // Решает проблема 1: создание view. ресурсозатратный, но вызвывается каждого элемента списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        Log.d("MY", "create view holder ${++createViewHolderCount}")
        val layout = when (viewType) {
            VIEW_LAYOUT_TYPE_1 -> R.layout.view_item
            VIEW_LAYOUT_TYPE_2 -> R.layout.view_item_modified
            else -> {
                R.layout.view_item
            }
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ListViewHolder((view))
    }

    //Определяет как вставить значения внутри view
//Вызывается 10000 раз уже по числу элементов в списке
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        //Log.d("MY", "bind view holder ${++bindViewHolderCount}")
        holder.textView.text = items[position]

        holder.itemView.setOnLongClickListener {
            onLongClick?.invoke(items[position])

            true
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    //какой макет используем для какого элемента
    override fun getItemViewType(position: Int): Int {
        if (items[position].toInt() % 10 == 0) {
            return VIEW_LAYOUT_TYPE_1
        } else return VIEW_LAYOUT_TYPE_2
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Решает проблема 2: поиск view для присвоения текста.
        // очень медленный. Но нужно вызвать findViewById для каждого элемента списка
        // а если нужно присвоить не только текст, но и еще поля?
        // Вызовется только 20 раз
        val textView: TextView = itemView.findViewById<TextView>(R.id.tv_item)
    }

    companion object {
        const val VIEW_LAYOUT_TYPE_1 = 1
        const val VIEW_LAYOUT_TYPE_2 = 2
        //переменные для размера пула разных
        const val VIEW_LAYOUT_TYPE_1_POOL_SIZE = 4
        const val VIEW_LAYOUT_TYPE_2_POOL_SIZE = 24
    }
}