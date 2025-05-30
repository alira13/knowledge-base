package com.example.designpatterns.observer.classic

// Где T-тип данных, на изменения которых мы подписываемся, например список строк
interface Observer<T> {
    fun onDataChanged(newData: T)
}