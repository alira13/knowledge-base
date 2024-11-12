package com.example.dagger2.di_like_dagger.di

import com.example.dagger2.di_like_dagger.data.Notebook
import com.example.dagger2.di_like_dagger.presentation.MainActivity


class Component {
    fun inject(activity: MainActivity) {
        activity.notebook = Notebook(
        )
    }

    fun provideNotebook(): Notebook {
        return Notebook(

        )
    }
}