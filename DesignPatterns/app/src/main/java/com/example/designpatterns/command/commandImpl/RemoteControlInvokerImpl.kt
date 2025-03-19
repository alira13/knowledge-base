package com.example.designpatterns.command.commandImpl

import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

// вот тут в реализации и описываем по какой логике хотим исполнять команды. В данном случае класть
// в очередь и потом вытаскивать первую в очереди и выполнять
class RemoteControl : Invoker<DeviceCommand> {
    //тип данных, который блокирует поток, пока очередь пуста
    // когда в очереди что-то появляется, take берет первый элемент
    // и удаляет его
    private val commands = LinkedBlockingQueue<DeviceCommand>()

    override fun executeCommand(command: DeviceCommand) {
        thread {
            commands.add(command)
            while (true) {
                //println("Waiting...")
                val existedCommand = commands.take()
                //println("Executing...$existedCommand")
                existedCommand.execute()
                //println("Executed...$existedCommand")
            }
        }
    }
}