package getterSetter

enum class LogLevel {
    TRACE, DEBUG, INFO, WARN, ERROR, FATAL
}

object Config {
    var timeout: Int = 30
        set(timeout) {
            if (timeout > 300 || timeout < 1) {
                println("Ошибка: Время ожидания должно быть в диапазоне от 1 до 300 секунд. Установлено значение по умолчанию.")
                field = 30
            } else
                field = timeout
        }

    var maxRetries: Int = 3
        set(maxRetries: Int) {
            if (maxRetries < 0) {
                println(
                    "Ошибка: Максимальное количество повторных попыток не может быть отрицательным. Установлено значение по умолчанию."
                )
                field = 3
            } else
                field = timeout
        }


    var loggingLevel: LogLevel = LogLevel.INFO
        set(loggingLevel) {
            if (loggingLevel == LogLevel.TRACE || loggingLevel == LogLevel.FATAL) {
                println("Ошибка: Уровень $loggingLevel недоступен.")
                field = LogLevel.INFO
                val st = StringBuilder()
            } else
                field = loggingLevel
        }

    val isDebugMode: Boolean
        get() = loggingLevel == LogLevel.DEBUG


    val isProductionMode: Boolean
        get() = loggingLevel == LogLevel.ERROR


    fun printConfig() {
        println(buildString {
            append("Время ожидания: <timeout> секунд\n")
            append("Максимальное количество повторных попыток: $maxRetries\n")
            append("Уровень логирования:$loggingLevel\n")
            append("Режим отладки :$isDebugMode\n")
            append("Режим продакшн :$isProductionMode")
        })
    }
}