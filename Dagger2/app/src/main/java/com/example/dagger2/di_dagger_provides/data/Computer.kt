package com.example.dagger2.di_dagger_provides.data

import javax.inject.Inject

class Computer @Inject constructor(
    val monitor: Monitor,
    val computerTower: ComputerTower,
    val keyboard: Keyboard,
    val mouse: Mouse

)
{

}
