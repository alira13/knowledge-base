package com.example.dagger2.di_full_project.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dagger2.R
import com.example.dagger2.di_full_project.app.ExampleApp
import javax.inject.Inject

class AnotherMainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        )[ExampleViewModel::class.java]
    }

    private val anotherViewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        )[AnotherExampleViewModel::class.java]
    }


    /* use custom Builder
    // create dataModule via builder,
    // another modules with empty constructors dagger create automatically
    // use lazy initialization because context isn ready yet
    val component by lazy {
        DaggerAppComponent.builder()
            .context(application)
            .logTagName("MY_MY_LOG")
            .build()
    }
    */

    // use custom Factory
    val component by lazy {
        (application as ExampleApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        // cant use because dataModule constructor isn't empty
        //DaggerAppComponent.create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //inject when context is ready
        component.inject(this)


        viewModel.method()
        Log.d("MY_PRES_LOG", "$this, $viewModel")

        anotherViewModel.method()
        Log.d("MY_PRES_LOG", "$this, $anotherViewModel")

    }
}