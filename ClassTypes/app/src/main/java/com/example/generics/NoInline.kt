package com.example.generics

private fun main() {
    doSmth(
        { println("Command1") },
        { println("Command1") }
    )
}

// noinline
private inline fun doSmth(command1: () -> Unit, noinline command2: () -> Unit) {
    command1()
    // 1. когда мы хотим передать lambda в другой метод, который был передан как параметр, мы должны сделать его noinline
    // 2. когда command2 слишком большая и мы не хотим ее встраивать
    useCommand(command2)
}

private fun useCommand(command: () -> Unit) {
    command()
}