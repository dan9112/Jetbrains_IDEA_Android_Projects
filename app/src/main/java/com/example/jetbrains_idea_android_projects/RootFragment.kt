package com.example.jetbrains_idea_android_projects

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetbrains_idea_android_projects.databinding.FragmentRootBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class RootFragment : Fragment() {
    private var subject: PublishSubject<String>? = null
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

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

            disposable?.dispose()
            subject = PublishSubject.create()

            dataObservable.zipWith(intervalObservable) { source, _ ->
                Log.i("Rx", "Flowable value - $source")
                source
            }.subscribe(object : DisposableObserver<String>() {
                override fun onNext(t: String) = subject!!.onNext(t)
                override fun onError(e: Throwable) = subject!!.onError(e)
                override fun onComplete() = subject!!.onComplete()
            }.also {
                disposable = it
            })
        }
        createNewSubscriber.setOnClickListener {
            subject?.subscribe(object : DisposableObserver<String>() {
                override fun onNext(s: String) {
                    Log.i("Rx", "$this onNext: $s")
                    // if (s == "...т") {
                    //     dispose()
                    //     Log.i("Rx", "$this has been disposed")
                    // }
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
            }) ?: Log.i("Rx", "")
        }
        disposeObservable.setOnClickListener {
            when {
                disposable == null -> {
                    Log.i("Rx", "Disposable is not initialized!")
                }

                disposable!!.isDisposed -> {
                    Log.i("Rx", "Disposable has already been disposed of before")
                }

                else -> {
                    disposable!!.dispose()
                    Log.i("Rx", "Disposable has been disposed")
                }
            }
        }
        root
    }
}
