package com.example.flowandchannels.flowOnPractice

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

object Repository {

    private const val API_KEY = "ibhmdv0SL162/QwjsRYmRA==fkpwuSDrNiyEAgzD"
    private const val BASE_URL = "https://api.api-ninjas.com/v1/dictionary?word="
    private const val HEADER_API_KEY = "X-Api-Key"

    // чтобы каждый раз при загрузке не создавать объект json
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun loadDefinition(word: String): List<String> {
        return withContext(Dispatchers.IO) {
            var connection: HttpURLConnection? = null
            try {
                // есть адрес в виде строки
                val urlString = BASE_URL + word
                // преобразуем его в объект url
                val url = URL(urlString)
                // аналог нажатия на кнопку Найти, чтобы появились методы чтения, преобразуем к HttpURLConnection
                connection = url.openConnection() as HttpURLConnection
                // добавили параметры
                connection.addRequestProperty(HEADER_API_KEY, API_KEY)
                // читаем из потока ввода из байтов и преобразуем в строку
                val response = connection.inputStream.bufferedReader().readText()
                json.decodeFromString<Definition>(response).mapDefinitionToList()
            } catch (e: Exception) {
                println(e.message.toString())
                emptyList<String>()
            } finally {
                connection?.disconnect()
            }
        }
    }
}

private fun Definition.mapDefinitionToList(): List<String> {
    return this.definition.split(Regex("\\d. ")).map { it.trim() }.filter { it.isNotEmpty() } // 1.
}

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)
fun main() {
    scope.launch {
        val resp = Repository.loadDefinition("computer")
        println(resp)
    }
}