package com.example.designpatterns.command.commandImpl

class Light {
    fun turnOn() = println("💡 Свет включен")
    fun turnOff() = println("🌑 Свет выключен")
}

class TV {
    fun turnOn() = println("📺 Телевизор включен")
    fun turnOff() = println("📺 Телевизор выключен")
    fun changeChannel(channel: Int) = println("📺 Канал переключен на $channel")
}

class AirConditioner {
    fun turnOn() = println("❄️ Кондиционер включен")
    fun turnOff() = println("🔥 Кондиционер выключен")
    fun setTemperature(temp: Int) = println("🌡️ Температура установлена на $temp°C")
}