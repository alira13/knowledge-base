package com.example.designpatterns.observer.mutableObservable

// Где T-тип данных, на изменения которых мы подписываемся, например список строк
fun interface Observer<T> {
    fun onDataChanged(newData: T)
}