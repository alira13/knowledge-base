package com.example.recylcerview.core

data class ShopItem(val name: String, var count: Int = 1, var id: Int = UNDEFINED_ID) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}