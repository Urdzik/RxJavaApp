package com.example.rxjava_lesson_one.model

import android.annotation.SuppressLint
import com.example.rxjava_lesson_one.MvpContract
import com.example.rxjava_lesson_one.MvpContract.Model.OnFinishedListener
import com.example.rxjava_lesson_one.launchBackgroundTask
import io.reactivex.disposables.Disposable

class DataModel : MvpContract.Model {

    @SuppressLint("CheckResult")
    override fun loadData(onFinishedListener: OnFinishedListener): Disposable =

        retrofitObject.retrofitService.message()
            .launchBackgroundTask({
                println("${it.size} - data size")

                onFinishedListener.onFinished(it)
            }, {
                onFinishedListener.onError(it)
            })

}