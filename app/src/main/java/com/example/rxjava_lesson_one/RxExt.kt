package com.example.rxjava_lesson_one

import android.annotation.SuppressLint
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.launchBackgroundTask(
    blockOnNext: (t: T) -> Unit,
    blockOnError: (throwable: Throwable) -> Unit
): Disposable {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .retry(3)
        .subscribe({
            blockOnNext(it)
        }, {
            blockOnError(it)
        })
}


fun <T> Flowable<T>.launchBackgroundTask(
    blockOnNext: (t: T) -> Unit,
    blockOnError: (throwable: Throwable) -> Unit
): Disposable {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .retry(3)
        .subscribe({
            blockOnNext(it)
        }, {
            blockOnError(it)
        })
}

@SuppressLint("CheckResult")
fun <T> Observable<T>.launchBackgroundTask(
    blockOnNext: (t: T) -> Unit,
    blockOnError: (throwable: Throwable) -> Unit
): Disposable {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .retry(3)
        .subscribe({
            blockOnNext(it)
        }, {
            blockOnError(it)
        })
}

fun <T> createRxSingle(sourceBlock: () -> T): Single<T> =
    Single.fromCallable { sourceBlock() }

//sealed class ResponseState {
//    object Loading: ResponseState
//
//    data class Content(): ResponseState() {
//
//    }
//}