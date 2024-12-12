package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_APP_NAME = "param1"
private const val ARG_ACTION_NAME = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PrepareFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PrepareFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var appName: String? = null
    private var actionName: String? = null

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            appName = it.getString(ARG_APP_NAME)
            actionName = it.getString(ARG_ACTION_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prepare, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById<TextView>(R.id.prepare_text_view)
        textView.text = "$appName is preparing for $actionName..."
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PrepareFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_APP_NAME, param1)
                    putString(ARG_ACTION_NAME, param2)
                }
            }
    }
}