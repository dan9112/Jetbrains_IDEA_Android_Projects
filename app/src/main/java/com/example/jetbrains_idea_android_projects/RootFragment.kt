package com.example.jetbrains_idea_android_projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetbrains_idea_android_projects.databinding.FragmentRootBinding

class RootFragment : Fragment() {
    private var _binding: FragmentRootBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRootBinding.inflate(inflater, container, false).run {
        _binding = this
        root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = RootFragment().apply {
            arguments = Bundle()
        }
    }
}
