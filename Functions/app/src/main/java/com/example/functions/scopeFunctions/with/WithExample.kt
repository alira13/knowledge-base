package com.example.functions.scopeFunctions.with

fun formatText(title: String, body: List<String>, footer: String): String {
    require(title.isNotBlank()) { "Title must not be blank" }
    require(body.isNotEmpty()) { "Body must contain at least one paragraph" }
    require(footer.isNotBlank()) { "Footer must not be blank" }

    val debugKeyword = "debug"
    val oldHeader = "=== Начало текста ==="
    val newHeader = "=== Новый заголовок ==="
    val maxTextLength = 500

    return with(StringBuilder()) {
        append("=== $title ===\n")
        body.forEach { paragraph -> append("$paragraph\n") }
        append("--- $footer ---\n")
        insert(0, "\n$oldHeader\n")
        append("\n=== Конец текста ===")
        val debugIndex = indexOf(debugKeyword)
        if (debugIndex != -1) {
            delete(debugIndex, debugIndex + debugKeyword.length)
        }

        val oldHeaderIndex = indexOf(oldHeader)
        if (oldHeaderIndex != -1) {
            replace(oldHeaderIndex, oldHeaderIndex + oldHeader.length, newHeader)
        }

        if (length > maxTextLength) {
            setLength(maxTextLength)
        }
        toString()
    }
}

fun main() {
    val formatted = formatText("My title", listOf("This", "is", "body"), "-------")
    println(formatted)
}