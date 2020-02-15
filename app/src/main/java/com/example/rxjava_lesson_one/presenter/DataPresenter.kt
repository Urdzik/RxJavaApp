package com.example.rxjava_lesson_one.presenter

import com.example.rxjava_lesson_one.MvpContract
import com.example.rxjava_lesson_one.model.Data
import com.example.rxjava_lesson_one.model.DataModel
import io.reactivex.disposables.Disposable

class DataPresenter(
    private val dataView: MvpContract.View
): MvpContract.Presenter, MvpContract.Model.OnFinishedListener {

    private val model = DataModel()
    private lateinit var dataDisposable: Disposable

    override fun onFinished(data: List<Data>) {
        println("${data.size} - data from presenter")
        dataView.retreiveData(data)
    }

    override fun onError(t: Throwable) {
        dataView.onError(t)
    }

    override fun requestData() {
        dataDisposable = model.loadData(this)
    }

    override fun onDestroy() {
        dataDisposable.dispose()
    }
}