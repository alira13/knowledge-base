package com.example.dagger2.di_full_project.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dagger2.R
import com.example.dagger2.di_full_project.di.DaggerAppComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: ExampleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        // create methods exist when constructors in all modules are empty
        DaggerAppComponent.create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.method()
    }
}