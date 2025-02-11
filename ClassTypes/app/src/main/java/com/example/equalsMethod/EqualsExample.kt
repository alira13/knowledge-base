package com.example.equalsMethod

class Man(val age: Int) {
    override fun toString(): String {
        return "Man(age=$age)"
    }
}

class ManWithEquals(private val age: Int) {
    override fun equals(other: Any?): Boolean {
        if (other !is ManWithEquals) return false
        return this.age == other.age
    }

    override fun toString(): String {
        return "ManWithEquals(age=$age)"
    }

    override fun hashCode(): Int {
        return age
    }

    // по-хорошему еще нужно переопределить hashCode
}


fun main() {
    // по умолчанию equals сравнивает равны ли ссылки false
    val person1 = Man(10)
    val person2 = Man(10)
    println(person1 == person2)

    // сравниваем по полям true
    val person3 = ManWithEquals(30)
    val person4 = ManWithEquals(30)
    println(person3 == person4)

    // сравниваем по ссылкам false
    println(person3 === person4)

    // в списке хранятся неуникальные значения
    val personList1 = mutableListOf<Man>(person1)
    //if (!personList1.contains(person2))
    //    personList1.add(person2)

    var exist = false
    for (person in personList1)
        if (person == person2) exist = true

    if (!exist) personList1.add(person2)

    println("personList2 " + personList1)
    // в множестве должны быть уникальные значения,
    // но так как hashCode не переопределен, то сравниваются по ссылкам,
    // ссылки разные, значит и элементы считаются разными
    val personSet1 = personList1.toSet()
    println("personSet1 " + personSet1)

    // тут будут одинаковы и список и множество, потому что поля не анализируются,
    // сравнение идет по ссылкам, а они разные, значит и элементы будут разные
    val personList2 = mutableListOf(person3)

    if (!personList2.contains(person4))
        personList2.add(person4)
    println("personList2 " + personList2)

    val personSet2 = mutableSetOf(person3)
    if (!personSet2.contains(person4)) personSet2.add(person4)
    println("personSet2 " + personSet2)
}