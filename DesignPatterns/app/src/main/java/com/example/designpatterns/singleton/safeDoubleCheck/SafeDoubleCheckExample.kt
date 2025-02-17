package com.example.designpatterns.singleton.safeDoubleCheck

class DatabaseConnection private constructor() {

    companion object {
        private var instance: DatabaseConnection? = null
        private var lock = Any()

        fun getInstance(): DatabaseConnection {
            instance?.let { return it }
            synchronized(lock) {
                instance?.let { return it }
                return DatabaseConnection().also { instance = it }
            }
        }
    }

    fun query(sql: String): String {
        return "Результат запроса: $sql"
    }
}

fun main() {
    println(DatabaseConnection.getInstance().query("1234"))
}