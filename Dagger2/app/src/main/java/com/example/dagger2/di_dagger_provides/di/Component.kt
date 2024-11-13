package com.example.dagger2.di_dagger_provides.di

import com.example.dagger2.di_dagger_provides.data.Computer
import com.example.dagger2.di_dagger_provides.data.ComputerTower
import dagger.Component

@Component(modules = [InDevicesModule::class, OutDevicesModule::class])
interface Component {
    fun getComputerTower(): ComputerTower

    fun getComputer(): Computer
}