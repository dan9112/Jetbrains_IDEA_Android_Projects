package com.example.jetbrains_idea_android_projects

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetbrains_idea_android_projects.databinding.FragmentRootBinding
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subscribers.DisposableSubscriber

class RootFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRootBinding.inflate(inflater, container, false).run {
        button.setOnClickListener {

            val  flowable = Flowable.fromArray("Кошачья правда", "Игривые кошки", "Кот лета")

            val subscriber = object : DisposableSubscriber<String>() {
                override fun onNext(s: String) {
                    Log.i("Rx", "onNext: $s")
                    if (s.equals("Игривые кошки", true)) {
                        dispose()
                        Log.i("Rx", "Disposed")
                    }
                }

                override fun onComplete() {
                    Log.i("Rx", "onComplete")
                }

                override fun onError(e: Throwable) {
                    Log.i("Rx", "onError: $e")
                }

            }
            flowable.subscribe(subscriber)
        }
        root
    }
}
