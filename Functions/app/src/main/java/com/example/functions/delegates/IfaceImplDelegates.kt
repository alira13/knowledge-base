package com.example.functions.delegates

fun main() {
    val human = Human("Max")
    val zombie = Zombie("Ivan")

    human.run()
    zombie.run()
    human.fight()
    zombie.fight()

    val premiumPlayer = PremiumPlayer(human)
    premiumPlayer.alive()

    val flyingPlayer = FlyingPlayer(zombie)
    flyingPlayer.fly()
    flyingPlayer.fight()
    flyingPlayer.run()
}

interface Player {
    val userName: String
    fun run()
    fun fight()
}

data class Human(override val userName: String) : Player {
    override fun run() {
        println("$userName runs fast")
    }

    override fun fight() {
        println("$userName shuts gun")
    }
}

data class Zombie(override val userName: String) : Player {
    override fun run() {
        println("$userName runs slow")
    }

    override fun fight() {
        println("$userName eats human")
    }
}

data class PremiumPlayer(val player: Player) : Player {
    override val userName: String
        get() = player.userName

    override fun run() {
        player.run()
    }

    override fun fight() {
        player.fight()
    }

    fun alive() {
        println("$userName is alive")
    }
}

// пример с делегированием реализации интерфейса
data class FlyingPlayer(val player: Player) : Player by player {
    override val userName: String
        get() = player.userName

    // чтобы не писать повторяющийся код, мы просто делегировали переопределение методов обхекту player
    /*
    override fun run() {
        player.run()
    }

    override fun fight() {
        player.fight()
    }
     */

    fun fly() {
        println("$userName fly")
    }
}