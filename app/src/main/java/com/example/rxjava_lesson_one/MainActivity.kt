package com.example.rxjava_lesson_one

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import io.reactivex.subjects.UnicastSubject
import java.util.concurrent.TimeUnit


//    private val filterOnly: Predicate<String> = Predicate { t ->
//        t.contains("5")
//    }
//
//    var zipIntWithString: BiFunction<Int?, String?, String?> = BiFunction { t1, t2 ->
//        "$t1: $t2"
//    }


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val observable: Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)
//
//        val observer: Consumer<Long> = Consumer { t ->
//            Log.d("One2", "$t")
//        }
//        val subscription = observable.subscribe(observer)
//
//        window.decorView.postDelayed({
//            Log.d("One2", "unsubscribe")
//            subscription.dispose()
//        }, 4500)


        s()
    }

    fun s() {
        val observer1 = object : Observer<Long> {
            override fun onComplete() {
                log("observer1 onComplete")
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Long) {
                log("observer1 onNext $t")
            }

            override fun onError(e: Throwable) {

            }

        }

        val observer2 = object : Observer<Long> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onComplete() {
                log("observer2 onComplete")

            }

            override fun onNext(t: Long) {
                log("observer2 onNext $t")
            }

            override fun onError(e: Throwable) {

            }

        }

        val observable = Observable.interval(1, TimeUnit.SECONDS).take(10)

        val subject:   UnicastSubject<Long> = UnicastSubject.create()

        log("subject subscribe")
        observable.subscribe(subject)



        Handler().postDelayed({
            log("observer1 subscribe")
            subject.subscribe(observer1)
        }, 3500)

        Handler().postDelayed({
            log("observer2 subscribe")
            subject.subscribe(observer2)
        }, 5500)

        window.decorView.postDelayed({
            subject.onNext(100L)
        }, 7500)
    }
}


fun log(s: String) {
    Log.d("one2", s)

}