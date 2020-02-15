package com.example.rxjava_lesson_one

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjava_lesson_one.modal.database.User
import com.example.rxjava_lesson_one.modal.database.UserDatabase
import com.example.rxjava_lesson_one.modal.database.UserDatabaseDAO
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_edit_text.*


class EditTextActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)

        listenerEditText()
        val dataSource = UserDatabase.getDatabase(applicationContext).userDatabaseDAO
        RxView.clicks(button).subscribe { click(dataSource) }
        RxView.clicks(button2).subscribe { show(dataSource) }


    }

    @SuppressLint("CheckResult")
    fun listenerEditText() {
        val name =
            RxTextView
                .textChanges(nameEditText)
                .map { t -> t.length > 2 }
                .distinctUntilChanged()

        name
            .map { b -> if (b) Color.BLACK else Color.RED }
            .subscribe { c -> nameEditText.setTextColor(c) }

        val age =
            RxTextView
                .textChanges(ageEditText)
                .map { t -> t.length > 1 }
                .distinctUntilChanged()
        age
            .map { b -> if (b) Color.BLACK else Color.RED }
            .subscribe { c -> ageEditText.setTextColor(c) }

        val isSingUpPossible = Observables
            .combineLatest(name, age) { e, p -> e && p }
            .distinctUntilChanged()

        isSingUpPossible.subscribe { b ->
            button.isEnabled = b
            button.isClickable = b
        }


    }

    private fun click(data: UserDatabaseDAO) {
        val newUser = User()
        newUser.name = nameEditText.text.toString()
        newUser.age = ageEditText.text.toString().toInt()
        Completable.fromAction {
            data.insert(newUser)
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe(object : CompletableObserver {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    Log.d("one2", e.toString())
                }

            })

    }

    @SuppressLint("CheckResult")
    private fun show(data: UserDatabaseDAO) {
        data.getAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                for (i in it) {
                    textView3.text = i.name
                }
            }
    }
}
