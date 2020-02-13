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

    }
}
