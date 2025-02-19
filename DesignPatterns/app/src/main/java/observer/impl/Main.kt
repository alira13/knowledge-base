package observer.impl

import kotlin.concurrent.thread
import kotlin.random.Random

fun main() {
    val repository = UserRepository()
    val userLogger1 = UserLogger("LOGGER1")
    val userLogger2 = UserLogger("LOGGER2")
    repository.addOnDataChangeListener(userLogger1)
    repository.addOnDataChangeListener(userLogger2)

    thread {
        while (true) {
            val num = Random.nextInt(0, 10)
            if (num % 2 == 0) repository.addUser("User$num")
            else repository.removeUser("User$num")
            Thread.sleep(1000)
        }
    }

    thread {
        Thread.sleep(5000)
        repository.deleteOnDataChangeListener(userLogger1)
    }
}