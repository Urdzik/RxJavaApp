package com.example.rxjava_lesson_one

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import io.reactivex.Observable
import io.reactivex.functions.Consumer
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

        val observable: Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)

        val observer: Consumer<Long> = Consumer { t ->
            Log.d("One2", "$t")
        }
        val subscription = observable.subscribe(observer)

        window.decorView.postDelayed({
            Log.d("One2", "unsubscribe")
            subscription.dispose()
        }, 4500)
    }






}
