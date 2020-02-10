package com.example.rxjava_lesson_one

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val data = retrofitObject
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val flowable = data.retrofitService.massage()
        disposable = flowable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSubscriber<List<Data>>() {
                override fun onComplete() {
                    log("onComplete")
                }

                override fun onNext(t: List<Data>) {

                }

                override fun onError(t: Throwable?) {
                    log("$t")
                }

            })
    }

    override fun onPause() {
        super.onPause()
        log("Dispose")
        disposable.dispose()
    }
}

fun log(s: String) {
    Log.d("one2", s)

}