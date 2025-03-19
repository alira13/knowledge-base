package com.example.designpatterns.command.commandImpl

sealed interface DeviceCommand : Command {
    class LightOnCommand(private val light: Light) : DeviceCommand {
        override fun execute() {
            light.turnOn()
        }
    }

    class LightOffCommand(private val light: Light) : DeviceCommand {
        override fun execute() {
            light.turnOff()
        }
    }

    class TVOnCommand(private val tv: TV) : DeviceCommand {
        override fun execute() {
            tv.turnOn()
        }
    }

    class TVOffCommand(private val tv: TV) : DeviceCommand {
        override fun execute() {
            tv.turnOff()
        }
    }

    class TVChangeChannelCommand(private val tv: TV, private val channel: Int) : DeviceCommand {
        override fun execute() {
            tv.changeChannel(channel)
        }
    }

    class AirConditionerOnCommand(private val ac: AirConditioner) : DeviceCommand {
        override fun execute() {
            ac.turnOn()
        }
    }

    class AirConditionerOffCommand(private val ac: AirConditioner) : DeviceCommand {
        override fun execute() {
            ac.turnOff()
        }
    }

    class AirConditionerSetTempCommand(private val ac: AirConditioner, private val temp: Int) :
        DeviceCommand {
        override fun execute() {
            ac.setTemperature(temp)
        }
    }
}