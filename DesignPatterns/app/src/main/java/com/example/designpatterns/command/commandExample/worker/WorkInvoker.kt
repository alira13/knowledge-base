package com.example.designpatterns.command.commandExample.worker

import com.example.designpatterns.command.commandExample.Invoker
import com.example.designpatterns.command.commandImpl.Command
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

object WorkInvoker : Invoker<WorkerCommands> {

    //тип данных, который блокирует поток, пока очередь пуста
    // когда в очереди что-то появляется, take берет первый элемент
    // и удаляет его
    private val commands = LinkedBlockingQueue<Command>()

    init {
        thread {
            while (true) {
                println("Waiting...")
                val command = commands.take()
                println("Executing...$command")
                command.execute()
                println("Executed...$command")
            }
        }
    }

    override fun addCommand(command: Command) {
        println("Add new command $command")
        commands.add(command)
    }
}