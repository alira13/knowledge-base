package com.example.dagger2.di_dagger_provides.di

import com.example.dagger2.di_dagger_provides.data.Keyboard
import com.example.dagger2.di_dagger_provides.data.Mouse
import dagger.Module
import dagger.Provides

@Module
class InDevicesModule {
    @Provides
    fun provideKeyboard(): Keyboard {
        return Keyboard()
    }

    @Provides
    fun provideMouse(): Mouse {
        return Mouse()
    }
}