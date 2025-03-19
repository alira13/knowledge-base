@file:RequiresApi(Build.VERSION_CODES.O)

package com.example.functions.delegates


import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Base64
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


private fun main() {
    val user = User()
    user.creditCardNumber = "1111 2222 3333 4444"
    user.password = "123"
    val decoded = user.readOnlyProperty
    println(">>>>>" + decoded)

    //println("card = ${user.creditCardNumber} password = ${user.password} ${user.someProp} ")
}

class User() {
    val someProp by lazy {
        "Created by lazy"
    }

    // создаем с помощью класса делегата
    var password by EncryptedProperty()

    // тоже самое что и верхняя строка
    var creditCardNumber by encrypted()

    // для read only property когда нам просто надо создать объект кем-то
    val readOnlyProperty: String by ReadOnlyEncryptedProperty()
}

// по аналогии с lazy метод который возвращает экземпляр делегата
private fun encrypted() = EncryptedProperty()

class EncryptedProperty() : ReadWriteProperty<User, String> {
    private var encryptedValue: String = ""

    override fun getValue(thisRef: User, property: KProperty<*>): String {
        println("Get value for $thisRef for property ${property.name}")
        val decoded = String(Base64.getDecoder().decode(encryptedValue))
        println("Decoded: $encryptedValue = $decoded")
        return (decoded)
    }

    override fun setValue(thisRef: User, property: KProperty<*>, value: String) {
        println("Set value for $thisRef for property ${property.name}")
        encryptedValue = String(Base64.getEncoder().encode(value.toByteArray()))
        println("Encoded: $value = $encryptedValue")
    }
}

class ReadOnlyEncryptedProperty() : ReadOnlyProperty<User, String> {

    override fun getValue(thisRef: User, property: KProperty<*>): String {
        return "I set some value for ${property.name}"
    }
}

