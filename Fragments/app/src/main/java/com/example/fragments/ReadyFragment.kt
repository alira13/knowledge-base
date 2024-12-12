package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class ReadyFragment : Fragment() {

    private lateinit var noButton: Button
    private lateinit var yesButton: Button
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ready, container, false)
    }

    //Точно знаем, что view уже создана
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        yesButton = view.findViewById(R.id.yes_button)
        noButton = view.findViewById(R.id.no_button)

        textView = view.findViewById(R.id.prepare_text_view)
        textView.text = "Are you ready?"
        yesButton.setOnClickListener({ textView.text = "Nice!" })
        noButton.setOnClickListener({ textView.text = "Ooops..." })
    }
}