package com.example.jetbrains_idea_android_projects

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetbrains_idea_android_projects.databinding.FragmentRootBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import java.util.concurrent.TimeUnit


class RootFragment : Fragment() {
    private var subject: Subject<String>? = null
    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRootBinding.inflate(inflater, container, false).run {
        startObservables.setOnClickListener {
            val intervalObservable = Observable.interval(1, TimeUnit.SECONDS)
            val dataObservable = Observable.fromArray(
                "Кошачья правда",
                "Игривые кошки",
                "Кот лета",
                "Рр-р-гх",
                "Коко",
                "Ко..,",
                "...т",
                "..."
            )

            if (subject?.hasComplete() != false) {
                disposables.clear()
                subject = BehaviorSubject.create()
            }
            dataObservable.zipWith(intervalObservable) { source, _ ->
                Log.i("Rx", "Observable value - $source")
                source
            }.subscribe(object : DisposableObserver<String>() {
                override fun onNext(t: String) = subject!!.onNext("$this - $t")
                override fun onError(e: Throwable) {
                    subject!!.onError(e)
                    subject = null
                }
                override fun onComplete() = subject!!.onComplete()
            }.also {
                disposables.add(it)
            })
        }
        createNewSubscriber.setOnClickListener {
            subject?.subscribe(object : DisposableObserver<String>() {
                override fun onNext(s: String) {
                    Log.i("Rx", "$this onNext: $s")
                    if (s == "...т") {
                        dispose()
                        Log.i("Rx", "$this has been disposed")
                    }
                }

                override fun onComplete() {
                    Log.i("Rx", "$this onComplete")
                }

                override fun onError(e: Throwable) {
                    Log.i("Rx", "$this onError: $e")
                }

                init {
                    Log.i("Rx", "$this has been subscribed")
                }
            }.also {
                disposables.add(it)
            }) ?: Log.i("Rx", "There is no active observable!")
        }
        disposeObservable.setOnClickListener {
            if (disposables.size() == 0) {
                Log.i("Rx", "There are no any active disposables!")
            } else {
                disposables.clear()
                Log.i("Rx", "Disposables has been disposed")
            }
        }
        root
    }
}
