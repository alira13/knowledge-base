package com.example.jetpackcompose.game.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackcompose.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    //1 Cоздать ссылку на объект binding
    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 2 Инициализация bimding создает класс на основе макета и создает там ссылки на все view
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        // 3 теперь нужно вернуть view
        //return inflater.inflate(R.layout.fragment_welcome, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 4 Использовать Binding
        val button = binding.btnAccept
    }

    // Удаляем ссылку на view чтобы если мы в каких-то методах обратились к view где она недоступна, сразу была ошибка
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}