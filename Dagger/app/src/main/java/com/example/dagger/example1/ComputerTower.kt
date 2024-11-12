package com.example.dagger.example1

import com.example.dependencyinjectionstart.example1.Memory

//dependency injection using constructor
class ComputerTower(
    val storage: Storage,
    val memory: Memory,
    val processor: Processor
)
