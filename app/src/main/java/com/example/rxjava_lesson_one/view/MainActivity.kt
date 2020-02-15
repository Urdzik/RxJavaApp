package com.example.rxjava_lesson_one.view
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxjava_lesson_one.MvpContract
import com.example.rxjava_lesson_one.R
import com.example.rxjava_lesson_one.databinding.ActivityMainBinding
import com.example.rxjava_lesson_one.model.Data
import com.example.rxjava_lesson_one.presenter.DataPresenter
import io.reactivex.disposables.Disposable


class MainActivity : AppCompatActivity(), MvpContract.View {

    private var adapter = MainAdapter()
    private var subscribe: Disposable? = null

    private lateinit var presenter: DataPresenter

    override fun retreiveData(data: List<Data>) {
        println("${data.size} - data from view")
        adapter.submitList(data)
    }

    override fun onError(t: Throwable) {
        println("ERRRORR - $t")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = DataPresenter(this)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        presenter.requestData()

        binding.rv.adapter = adapter

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
        subscribe?.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}

fun log(s: String) {
    Log.d("one2", s)

}
