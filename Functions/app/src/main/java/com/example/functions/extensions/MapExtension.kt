package com.example.functions.extensions

fun main() {
    val scores = mapOf(
        "Alice" to 95,
        "Bob" to 87,
        "Charlie" to 78
    )

    // Преобразуем оценки в текстовые категории
    val categorizedScores = scores.transformValues { score ->
        when {
            score >= 90 -> "Отлично"
            score >= 80 -> "Хорошо"
            score >= 70 -> "Удовлетворительно"
            else -> "Неудовлетворительно"
        }
    }


    // Ожидаемый результат: {Alice=Отлично, Bob=Хорошо, Charlie=Удовлетворительно}
    println(categorizedScores)
}

fun <K, V, R> Map<K, V>.transformValues(transform: (V) -> R): Map<K, R> {
    val resultMap = mutableMapOf<K, R>()
    for (itemValue in this) {
        val newValue = transform(itemValue.value)
        resultMap[itemValue.key] = newValue
    }
    return resultMap
}