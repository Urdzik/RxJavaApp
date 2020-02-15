package com.example.rxjava_lesson_one

import com.example.rxjava_lesson_one.model.Data
import io.reactivex.disposables.Disposable

interface MvpContract {

    interface Model {
        interface OnFinishedListener {
            fun onFinished(data: List<Data>)

            fun onError(t: Throwable)
        }

        fun loadData(onFinishedListener: OnFinishedListener): Disposable
    }

    interface Presenter {

        fun requestData()

        fun onDestroy()
    }

    interface View {
        fun retreiveData(data: List<Data>)

        fun onError(t: Throwable)
    }
}