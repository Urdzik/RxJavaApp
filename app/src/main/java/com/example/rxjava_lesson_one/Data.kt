package com.example.rxjava_lesson_one

import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class Data(
    val title : String,
    val text : String,
    val author : String,
    val ganre : String,
    val volume : String,
    val date : String,
    val url : String,
    val rating : Rating
)

data class Rating (

    val litres : Double,
    val liveLib : Double
)

interface WebApi {
    @GET("db.json")
    fun massage() : Flowable<List<Data>>
}

object retrofitObject {
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://raw.githubusercontent.com/Urdzik/dataBase/master/")
        .build()
    val retrofitService: WebApi = retrofit.create(WebApi::class.java)
}