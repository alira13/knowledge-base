package com.example.jetpackcompose.game.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackcompose.R
import com.example.jetpackcompose.databinding.FragmentGameBinding
import com.example.jetpackcompose.game.domain.entity.GameResult
import com.example.jetpackcompose.game.domain.entity.GameSettings
import com.example.jetpackcompose.game.domain.entity.Level


class GameFragment : Fragment() {

    private var level: Level? = null

    private var _binding: FragmentGameBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            level = it.getParcelable(ARG_LEVEL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        binding.ivMaxSumValue.setOnClickListener {
            launchGameFinishFragment(
                GameResult(
                    true, 0, 0,
                    GameSettings(0, 0, 0, 0)
                )
            )
        }
        return binding.root
    }

    private fun launchGameFinishFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.main, GameFinishFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        private const val ARG_LEVEL = "level"

        @JvmStatic
        fun newInstance(level: Level) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_LEVEL, level)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}