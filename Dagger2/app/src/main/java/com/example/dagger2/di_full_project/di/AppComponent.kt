package com.example.dagger2.di_full_project.di

import android.content.Context
import com.example.dagger2.di_full_project.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [DataModule::class, DomainModule::class])
interface AppComponent {
    fun inject(activity:MainActivity)

    /*
    // Способ 1(старые версии Dagger)
    // переопределяем интерфейс для билдера, добавляем ему еще методов
    @Component.Builder
    interface AppComponentBuilder {

        //insert to dependency graph some object without new module

        // create bindsInstance for context
        @BindsInstance
        fun context(context: Context): AppComponentBuilder

        // create bindsInstance for logTagName
        @BindsInstance
        fun logTagName(logTagName: String): AppComponentBuilder

        // build
        fun build(): AppComponent
    }
     */

    // Способ 2(Новые версии Dagger, предпочтительнее и удобнее)
    // Переопределяем фабрику, закидываем в метод Create все параметры и помечаем их BindsInstance
    @Component.Factory
    interface AppComponentFactory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance logTagName: String
        ): AppComponent
    }
}