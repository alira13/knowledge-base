package com.example.designpatterns.observer.simpliest


class UserRepository {
    private val _users = mutableListOf<String>()

    val users:List<String>
        get() {return _users}

    // 1 Добавить список подписчиков (наблюдателей) UserLogger
    private val listeners = mutableListOf<UserLogger>()

    fun addUser(user: String) {
        _users.add(user)
        println("Add user $user")
        // 2 Уведомить подписчиков об изменении списка пользователей
        notifyObservers()
    }

    fun removeUser(user: String) {
        _users.remove(user)
        println("Remove user $user")
        // 2 Уведомить подписчиков об изменении списка пользователей
        notifyObservers()
    }

    // 3 Реализовать метод подписки
    fun addOnDataChangeListener(listener: UserLogger) {
        listeners.add(listener)
        // хз надо ли
        //listener.onUsersChanged(users)
    }

    // 4 Реализовать метод отписки
    fun deleteOnDataChangeListener(listener: UserLogger) {
        listeners.remove(listener)
    }

    // 5 Реализовать метод уведомления подписчиков
    private fun notifyObservers() {
        listeners.forEach { it.onUsersChanged(_users) }
    }
}