package com.example.dagger2.di_dagger_inject

import dagger.Component

@Component
interface Component {
    fun inject(activity: MainActivity)

    fun getNotebook():Notebook
}