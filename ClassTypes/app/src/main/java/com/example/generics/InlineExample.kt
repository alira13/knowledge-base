package com.example.generics

private fun main() {
    val myList = (0..10).toList()


    myList.myFilter {
        // можем потому что inline
        // дойдет до 9 и выйдет из main, println не напечатает ничего
        // (it == 9) return - non local return(когда мы находимся в одной функции, но выходим из той, которая ее вызвала)
        it % 2 == 0
    }.forEach { println(it) }

    myList.myFilter(object : Condition<Int> {
        override fun isCorrect(item: Int): Boolean {
            // тут не можем
            // if (item==9) return
            return item % 2 == 0
        }
    })
}

// В byte-code реализация 1 и 2 будут абсолютно идентичны,
// вместо lambda будет создан объект интерфейсного типа Condition

// Проблемы:
// 1. нельзя вызвать suspend функцию внутри myFilter,
// так как все происходит в объекте Condition в методе isCorrect которые не suspend

// 2. каждый раз при вызове будет создаваться лишний объект Condition - долго и затратно по памяти.
// В случае inline он все еще будет аргументом в методе,
// но сам метод не будет нигде использоваться, будет напрямую вставляться код

// 3. нельзя прервать работу метода, который вызвал myFilter, в нашем случае main
// Решение  - сделать inline myFilter

//4. без inline не можем работать с типом дженерика T(reified используется только с inline)

// реализация 1.
private inline fun <T> List<T>.myFilter(lambda: (T) -> Boolean): List<T> {
    val resultList = mutableListOf<T>()
    this.forEach { if (lambda(it)) resultList.add(it) }
    return resultList
}

// реализация 2
private fun <T> List<T>.myFilter(condition: Condition<T>): List<T> {
    val resultList = mutableListOf<T>()
    this.forEach { if (condition.isCorrect(it)) resultList.add(it) }
    return resultList
}

interface Condition<T> {
    fun isCorrect(item: T): Boolean
}
