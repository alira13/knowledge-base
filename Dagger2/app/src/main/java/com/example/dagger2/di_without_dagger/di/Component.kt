package com.example.dagger2.di_without_dagger.di

import com.example.dagger2.di_without_dagger.presentation.MainActivity
import com.example.dagger2.di_without_dagger.data.Computer
import com.example.dagger2.di_without_dagger.data.ComputerTower
import com.example.dagger2.di_without_dagger.data.Keyboard
import com.example.dagger2.di_without_dagger.data.Memory
import com.example.dagger2.di_without_dagger.data.Monitor
import com.example.dagger2.di_without_dagger.data.Mouse
import com.example.dagger2.di_without_dagger.data.Processor
import com.example.dagger2.di_without_dagger.data.Storage

class Component {
    fun inject(activity: MainActivity) {
        activity.diComputerViaInject = Computer(
            monitor = Monitor(),
            mouse = Mouse(),
            keyboard = Keyboard(),
            computerTower = ComputerTower(Storage(), Memory(), Processor())
        )
    }

    fun provideComputer():Computer{
        return Computer(
            monitor = Monitor(),
            mouse = Mouse(),
            keyboard = Keyboard(),
            computerTower = ComputerTower(Storage(), Memory(), Processor())
        )
    }
}