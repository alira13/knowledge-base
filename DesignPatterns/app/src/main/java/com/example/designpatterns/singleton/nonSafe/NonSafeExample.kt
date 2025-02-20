package com.example.designpatterns.singleton.nonSafe

class SettingsManager private constructor(context: Context) : BaseManager(context) {

    private val settings: MutableMap<String, String> = mutableMapOf()

    init {
        settings.putAll(context.defaultSettings)
    }

    fun getSetting(key: String): String? {
        return settings[key]
    }

    companion object Impl1 {

        private var instance: SettingsManager? = null

        fun getInstance(context: Context): SettingsManager {
            if (instance == null) instance = SettingsManager(context)
            return instance!!
        }
    }
}

open class BaseManager(val context: Context)

data class Context(val name: String, val defaultSettings: Map<String, String>)

fun main() {
    val settingsManager = SettingsManager.getInstance(
        Context(
            "Context name",
            mapOf("Theme" to "Dark", "Logged" to "No")
        )
    )
    println(settingsManager.getSetting("Theme"))
}