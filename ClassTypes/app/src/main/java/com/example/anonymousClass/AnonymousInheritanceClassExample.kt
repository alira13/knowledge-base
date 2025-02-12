package com.example.anonymousClass

open class SomeClass1(val name: String)

open class SomeClass2(val name: String)

abstract class SomeAbstractClass1() {
    abstract fun someAbsractFun1()
}

abstract class SomeAbstractClass2() {
    abstract fun someAbsractFun2()
}

interface SomeInterface {
    fun someInterfaceFun()
}

interface AnotherInterface {
    fun someAnotherInterfaceFun()
}

fun main() {
    // анонимный класс может наследоваться от какого-то другого 1 класс(асбтрактного или обычного),
    // или реализовывать сколько угодно интерфейсов
    // как и обычный класс
    val child = object : SomeClass1("SomeClass"), SomeInterface, AnotherInterface {
        fun printName() {
            println(name)
        }

        override fun someInterfaceFun() {
            println("someInterfaceFun")
        }

        override fun someAnotherInterfaceFun() {
            println("someAnotherInterfaceFun")
        }
    }

    child.printName()
    child.someInterfaceFun()
    child.someAnotherInterfaceFun()
}


