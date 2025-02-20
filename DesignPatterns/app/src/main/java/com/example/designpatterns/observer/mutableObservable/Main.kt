package com.example.designpatterns.observer.mutableObservable

import kotlin.concurrent.thread
import kotlin.random.Random

fun main() {
    val repository = UserRepository()
    val userLogger1 = UserLogger("LOGGER1")
    val userLogger2 = UserLogger("LOGGER2")
    val userLogger3 = UserSizeLogger("LOGGER3")
    repository.observableData.addOnDataChangeListener(userLogger1)
    repository.observableData.addOnDataChangeListener(userLogger2)
    repository.observableDataSize.addOnDataChangeListener(userLogger3)
    repository.observableDataSize.addOnDataChangeListener { println("Im not a logger but data size was changed") }

    val userIds = mutableListOf<Int>(0, 10)

    thread {
        while (true) {
            val id = Random.nextInt(userIds.min(), userIds.max())
            userIds.add(id)
            val newUser = "User$id"
            if (repository.observableData.data.contains(newUser))
                repository.removeUser(newUser)
            else
                repository.addUser(newUser)
            Thread.sleep(1000)
        }
    }

    thread {
        Thread.sleep(5000)
        repository.observableData.deleteOnDataChangeListener(userLogger1)
    }
}