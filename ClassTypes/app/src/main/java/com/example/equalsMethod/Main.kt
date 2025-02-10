package com.example.equalsMethod


fun main() {
    // по умолчанию equals сравнивает равны ли ссылки false
    val person1: Any = Man(10)
    val person2 = Man(10)
    println(person1 == person2)

    // сравниваем по полям true
    val person3 = ManWithEquals(30)
    val person4 = ManWithEquals(30)
    println(person3 == person4)

    // сравниваем по ссылкам false
    println(person3 === person4)

    TestCases().runTests()
}