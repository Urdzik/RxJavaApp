package com.example.rxjava_lesson_one

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.rxkotlin.Observables
import kotlinx.android.synthetic.main.activity_edit_text.*


class EditTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)

//       fun sub() = nameEditText.textChanges().subscribe {
//           textView3.text = nameEditText.text.toString()
//       }
        s()

    }

    @SuppressLint("CheckResult")
    fun s() {
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
                .map { t -> t.length > 6 }
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
}
