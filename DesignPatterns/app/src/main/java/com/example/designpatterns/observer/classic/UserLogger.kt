package com.example.designpatterns.observer.classic


// Класс наблюдателя, который подписывается на изменения в UserRepository
class UserLogger(private val name: String) : Observer<List<String>> {
    // Метод onUsersChanged(), который выводит сообщение в консоль в формате:
    // [LOG] Пользователи обновлены: <список пользователей>
    override fun onDataChanged(newData: List<String>) {
        println("$name Users were updated: $newData")
    }
}