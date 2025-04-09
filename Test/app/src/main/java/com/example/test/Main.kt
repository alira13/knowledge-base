package com.example.test

fun main() {
    println("Hello world")
    val mess:MyMessage? = null
    when(mess){
        is HisMessage -> TODO()
        is YourMessage -> TODO()
        null -> TODO()
    }
}

sealed interface MyMessage {
    fun say()
}

class YourMessage(val message: String) : MyMessage {
    override fun say() {
        TODO("Not yet implemented")
    }
}