package com.example.dagger2.di_full_project.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dagger2.R
import com.example.dagger2.di_full_project.di.DaggerAppComponent
import com.example.dagger2.di_full_project.di.DataModule
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: ExampleViewModel

    // create dataModule via builder,
    // another modules with empty constructors dagger create automatically
    // use lazy initialization because context isn ready yet
    val component by lazy {
        DaggerAppComponent.builder()
            .dataModule(DataModule(this))
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        // cant use because dataModule constructor isn't empty
        //DaggerAppComponent.create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //inject when context is ready
        component.inject(this)

        viewModel.method()
    }
}