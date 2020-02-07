package com.example.rxjava_lesson_one

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


//    private val filterOnly: Predicate<String> = Predicate { t ->
//        t.contains("5")
//    }
//
//    var zipIntWithString: BiFunction<Int?, String?, String?> = BiFunction { t1, t2 ->
//        "$t1: $t2"
//    }


class MainActivity : AppCompatActivity() {

    private val data = retrofitObject
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

//        val observer1 = object : Observer<Long> {
//            override fun onComplete() {
//                log("observer1 onComplete")
//            }
//
//            override fun onSubscribe(d: Disposable) {
//
//            }
//
//            override fun onNext(t: Long) {
//                log("observer1 onNext $t")
//            }
//
//            override fun onError(e: Throwable) {
//
//            }
//
//        }
//
//        val observer2 = object : Observer<Long> {
//            override fun onSubscribe(d: Disposable) {
//
//            }
//
//            override fun onComplete() {
//                log("observer2 onComplete")
//
//            }
//
//            override fun onNext(t: Long) {
//                log("observer2 onNext $t")
//            }
//
//            override fun onError(e: Throwable) {
//
//            }
//
//        }
//
//        val observable = Observable.interval(1, TimeUnit.SECONDS).take(10)
//
//        val subject: UnicastSubject<Long> = UnicastSubject.create()
//
//        log("subject subscribe")
//        observable.subscribe(subject)
//
//
//
//        Handler().postDelayed({
//            log("observer1 subscribe")
//            subject.subscribe(observer1)
//        }, 3500)
//
//        Handler().postDelayed({
//            log("observer2 subscribe")
//            subject.subscribe(observer2)
//        }, 5500)
//
//        window.decorView.postDelayed({
//            subject.onNext(100L)
//        }, 7500)
//    }

//        val observer = object : Observer<Int?> {
//            override fun onComplete() {
//                log("observer completed")
//            }
//
//            override fun onSubscribe(d: Disposable) {
//
//            }
//
//            override fun onNext(t: Int) {
//                log("onNext value = $t")
//            }
//
//            override fun onError(e: Throwable) {
//
//            }
//        }
//
//        val func = Function<Int?, Int?> { t ->
//            log("func $t")
//            t * 10
//        }
//
//        val onSubscribe = ObservableOnSubscribe<Int?> { emitter ->
//            log("call")
//            for (i in 0..2) {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(1000)
//                } catch (e: InterruptedException) {
//                    e.printStackTrace()
//                }
//                emitter.onNext(i)
//            }
//            emitter.onComplete()
//        }
//
//        val observable: Observable<Int?> = Observable
//            .create(onSubscribe)
//            .subscribeOn(Schedulers.io())
//            .observeOn(Schedulers.computation())
//            .map(func)
//            .observeOn(AndroidSchedulers.mainThread())
//
//        log("subscribe")
//        observable.subscribe(observer)
//
//        log("done")
//
//
//    }


//        val stringData: Observable<String> = Observable.just("1", "2", "a", "4", "5")
//
//        val observable: Observable<Long> = stringData.map { t -> t.toLong() }
//            .subscribeOn(Schedulers.io())
//            .retry(2)
//


        val observable = data.retrofitService.massage(1)

        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Data>> {

                override fun onSubscribe(d: Disposable) {
                    log("onSubscribe")
                }


                override fun onComplete() {
                    log("onComplete")
                }

                override fun onError(e: Throwable) {
                    log("Exception $e")
                }

                override fun onNext(t: List<Data>) {
                    log("onNext ${t.size}")
                }
            })

    }


}


fun log(s: String) {
    Log.d("one2", s)

}