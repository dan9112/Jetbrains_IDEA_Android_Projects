package com.example.jetbrains_idea_android_projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetbrains_idea_android_projects.databinding.FragmentRootBinding
import io.reactivex.rxjava3.core.Observable

class RootFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRootBinding.inflate(inflater, container, false).run {
        Observable.just("Hello world!").subscribe { s -> textView.setText(s) }
        root
    }
}
