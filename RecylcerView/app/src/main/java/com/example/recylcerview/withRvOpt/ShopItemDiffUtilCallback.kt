package com.example.recylcerview.withRvOpt

import androidx.recyclerview.widget.DiffUtil
import com.example.recylcerview.core.ShopItem

class ShopItemDiffUtilCallback : DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}