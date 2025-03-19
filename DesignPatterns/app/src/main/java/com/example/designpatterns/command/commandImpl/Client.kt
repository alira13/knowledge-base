package com.example.designpatterns.command.commandImpl

/*
Ваша задача — реализовать паттерн "Команда" (Command Pattern),
чтобы управление устройствами осуществлялось через универсальный интерфейс команд.
Пульт (RemoteControl) должен принимать команды и выполнять их в фоне, обрабатывая их в порядке поступления.
 */

// Client - просто вызывает методы Invoker
fun runCommandTest() {
    val light = Light()
    val tv = TV()
    val ac = AirConditioner()
    val remote = RemoteControl()

    remote.executeCommand(DeviceCommand.LightOnCommand(light))
    remote.executeCommand(DeviceCommand.TVOnCommand(tv))
    remote.executeCommand(DeviceCommand.TVChangeChannelCommand(tv, 5))
    remote.executeCommand(DeviceCommand.AirConditionerOnCommand(ac))
    remote.executeCommand(DeviceCommand.AirConditionerSetTempCommand(ac, 22))
    remote.executeCommand(DeviceCommand.LightOffCommand(light))
    remote.executeCommand(DeviceCommand.TVOffCommand(tv))
    remote.executeCommand(DeviceCommand.AirConditionerOffCommand(ac))
}

fun main(){
    runCommandTest()
}

