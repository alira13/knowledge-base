package com.example.designpatterns.observer.simpliest

import kotlin.concurrent.thread
import kotlin.random.Random

fun main() {
    val repository = UserRepository()
    val userLogger1 = UserLogger("LOGGER1")
    val userLogger2 = UserLogger("LOGGER2")
    repository.addOnDataChangeListener(userLogger1)
    repository.addOnDataChangeListener(userLogger2)
    val userIds = mutableListOf<Int>(0, 10)

    thread {
        while (true) {
            val id = Random.nextInt(userIds.min(), userIds.max())
            userIds.add(id)
            val newUser = "User$id"
            if (repository.users.contains(newUser))
                repository.removeUser(newUser)
            else
                repository.addUser(newUser)
            Thread.sleep(1000)
        }
    }

    thread {
        Thread.sleep(5000)
        repository.deleteOnDataChangeListener(userLogger1)
    }
}