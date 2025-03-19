package com.example.designpatterns.command.commandExample

import com.example.designpatterns.observer.mutableObservable.MutableObservable
import com.example.designpatterns.observer.mutableObservable.Observable


// Receiver
class UserRepositoryReceiver {
    // Данные, с которыми мы работаем внутри репозитория
    private val _data = mutableListOf<String>()

    // 1 Данные, на изменение которых мы подписываемся
    private val _observableData =
        MutableObservable<List<String>>(_data)
    val observableData: Observable<List<String>>
        get() = _observableData

    //  Данные, на изменение которых мы подписываемся
    private val _observableDataSize =
        MutableObservable<Int>(_data.size)
    val observableDataSize: Observable<Int>
        get() = _observableDataSize

    // ---------------------методы репозитория ------------------------------------
    fun addUser(user: String) {
        _data.add(user)
        println("Add user $user")
        // уведомить подписчиков об изменении списка пользователей
        _observableData.data = _data
        _observableDataSize.data = _data.size
        Thread.sleep(5000)
    }

    fun removeUser(user: String) {
        _data.remove(user)
        println("Remove user $user")
        // уведомить подписчиков об изменении списка пользователей
        _observableData.data = _data
        _observableDataSize.data = _data.size
        Thread.sleep(3000)
    }
}