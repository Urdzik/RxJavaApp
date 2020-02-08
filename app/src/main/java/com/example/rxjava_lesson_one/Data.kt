package com.example.rxjava_lesson_one

import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class Data(
    val id: Long,
    val time: String,
    val text: String,
    val image: String
)

interface WebApi {
    @GET("messages{page}.json")
    fun massage(@Path("page") page: Int): Flowable<List<Data>>
}

object retrofitObject {
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
        .build()
    val retrofitService: WebApi = retrofit.create(WebApi::class.java)
}