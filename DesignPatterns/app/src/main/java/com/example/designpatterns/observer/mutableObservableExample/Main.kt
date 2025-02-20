package com.example.designpatterns.observer.mutableObservableExample

import kotlin.concurrent.thread
import kotlin.random.Random

// Класс для автоматического обновления данных
class DataUpdater(private val repository: DataRepository) {
    init {
        thread {
            while (true) {
                when (Random.nextInt(3)) {
                    0 -> repository.updateData(newUser = "User_${Random.nextInt(1, 100)}")
                    1 -> repository.updateData(newOrder = Random.nextInt(100, 200))
                    2 -> repository.updateData(newPrice = Random.nextDouble(50.0, 150.0))
                }
                val millis = Random.nextLong(10000L)
                Thread.sleep(millis) // Симуляция времени между обновлениями
            }
        }
    }
}

fun main() {
    // Запуск обновления данных
    DataUpdater(DataRepository)

    // Запуск мониторинга
    DataRepository.userData.registerObserver {
        println("UserMonitor: User data updated: $it")
    }

    DataRepository.priceData.registerObserver {
        println("PriceMonitor: Price was updated: $it")
    }

    DataRepository.orderData.registerObserver {
        println("OrderMonitor: Order data updated: $it")
    }
}