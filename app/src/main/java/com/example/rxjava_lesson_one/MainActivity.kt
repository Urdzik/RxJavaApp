package com.example.rxjava_lesson_one
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxjava_lesson_one.databinding.ActivityMainBinding
import com.example.rxjava_lesson_one.modal.Data
import com.example.rxjava_lesson_one.modal.retrofitObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber


class MainActivity : AppCompatActivity() {

    private val dataRetrofit = retrofitObject
    private var adapter = MainAdapter()
    private var subscribe: Disposable? = null
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        binding.rv.adapter = adapter


        val flowable = dataRetrofit.retrofitService.massage()
        disposable = flowable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSubscriber<List<Data>>() {
                override fun onComplete() {
                    log("onComplete")
                }

                override fun onNext(t: List<Data>) {
                    adapter.submitList(t)
                }

                override fun onError(t: Throwable?) {
                    log("$t")
                }

            })

        setupItemClick()

    }


    private fun setupItemClick() {
        subscribe = adapter.clickEvent
            .subscribe {
                AlertDialog.Builder(this)
                    .setTitle(it.title)
                    .setMessage(it.text)
                    .setPositiveButton("Done", null)
                    .create()
                    .show()

            }
    }

    override fun onPause() {
        super.onPause()
        log("Dispose")
        disposable.dispose()
        subscribe?.dispose()
    }

}

fun log(s: String) {
    Log.d("one2", s)

}