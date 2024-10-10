package com.example.uielements

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val spinner: Spinner = findViewById(R.id.spinner_sp)

        // c адаптером по умолчанию
        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this

        //Можно создать самим адаптер(чтобы заработало, надо добавить в адаптере реализации методов)

        /*   val spinner = findViewById<Spinner>(R.id.spinner_sp)
            val planets = listOf("Меркурий", "Венера", "Земля")
            val adapter: SpinnerAdapterExample = SpinnerAdapterExample(
               this,
               android.R.layout.simple_spinner_dropdown_item, planets
            )
            spinner.adapter = adapter
    spinner.onItemSelectedListener = this

         */
    }

    @SuppressLint("SetTextI18n")
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item is selected. You can retrieve the selected item using
        val spinnerItemInfo: TextView = findViewById(R.id.spinner_item_info_tv)
        spinnerItemInfo.text = "Выбранный элемент: ${parent.getItemAtPosition(pos).toString()}"
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        val spinnerItemInfo: TextView = findViewById(R.id.spinner_item_info_tv)
        spinnerItemInfo.text = "Ничего не выбрано"
        // Another interface callback.
    }
}