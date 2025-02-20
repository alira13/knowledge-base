package com.example.designpatterns.observer.mutableObservable

// Класс наблюдателя, который подписывается на изменения в UserRepository
abstract class Logger<T>(private val name: String) : Observer<T> {
    // Метод onUsersChanged(), который выводит сообщение в консоль в формате:
    // [LOG] Пользователи обновлены: <список пользователей>
    override fun onDataChanged(newData: T) {
        println("$name Data were updated: $newData")
    }
}

// Класс наблюдателя, который подписывается на изменения в UserRepository
class UserLogger(private val name: String) : Logger<List<String>>(name) {
    // Метод onUsersChanged(), который выводит сообщение в консоль в формате:
    // [LOG] Пользователи обновлены: <список пользователей>
    override fun onDataChanged(newData: List<String>) {
        println("$name Data were updated: $newData")
    }
}

// Класс наблюдателя, который подписывается на изменения в UserRepository
class UserSizeLogger(private val name: String) : Logger<Int>(name) {
    // Метод onUsersChanged(), который выводит сообщение в консоль в формате:
    // [LOG] Пользователи обновлены: <список пользователей>
    override fun onDataChanged(newData: Int) {
        println("$name Data size were updated: $newData")
    }
}