package com.example.recylcerview.withRvOpt

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.recylcerview.R
import com.example.recylcerview.core.ShopItem

// Наследуемся от ListAdapter, в котором не нужно переопределять часть методов,
// он принимает на вход ListItemDiffUtilCallback, который под капотом уже реализует сравнение
// элементов списка и делает это сравнение НЕ в главном потоке, что улучшает производительность отображения списка
class ShopListAdapter : ListAdapter<ShopItem, ShopListViewHolder>(ShopItemDiffUtilCallback()) {

    private var createViewHolderCount: Int = 0
    private var bindViewHolderCount: Int = 0

    var onLongClick: ((ShopItem) -> Any)? = null

    // Определяет как создать view(вызовется примерно 20(10 на экране + по 5 с запасом))
    // Решает проблема 1: создание view. ресурсозатратный, но вызвывается каждого элемента списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        Log.d("MY", "create view holder ${++createViewHolderCount}")
        val layout = when (viewType) {
            VIEW_LAYOUT_TYPE_1 -> R.layout.view_item
            VIEW_LAYOUT_TYPE_2 -> R.layout.view_item_modified
            else -> {
                R.layout.view_item
            }
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopListViewHolder((view))
    }

    //Определяет как вставить значения внутри view
    //Вызывается 10000 раз уже по числу элементов в списке но только при их обновлении
    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        Log.d("MYMYMY", "bind view holder ${++bindViewHolderCount}")
        holder.textView.text = getItem(position).name

        holder.itemView.setOnLongClickListener {
            onLongClick?.invoke(getItem(position))

            true
        }
    }


    //какой макет используем для какого элемента
    override fun getItemViewType(position: Int): Int {
        if (getItem(position).count % 2 == 0) {
            return VIEW_LAYOUT_TYPE_1
        } else return VIEW_LAYOUT_TYPE_2
    }

    companion object {
        const val VIEW_LAYOUT_TYPE_1 = 1
        const val VIEW_LAYOUT_TYPE_2 = 2

        //переменные для размера пула разных
        const val VIEW_LAYOUT_TYPE_1_POOL_SIZE = 4
        const val VIEW_LAYOUT_TYPE_2_POOL_SIZE = 24
    }
}