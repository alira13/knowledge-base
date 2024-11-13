package com.example.dagger2.di_dagger_provides.di

import com.example.dagger2.di_dagger_provides.data.Monitor
import dagger.Module
import dagger.Provides

@Module
class OutDevicesModule {
    @Provides
    fun provideMonitor(): Monitor {
        return Monitor()
    }
}