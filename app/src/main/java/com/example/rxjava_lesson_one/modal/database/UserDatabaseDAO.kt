package com.example.rxjava_lesson_one.modal.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface UserDatabaseDAO {

    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user_table")
    fun getAll(): Flowable<List<User>>

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getAllWithId(id: Long): Single<User>
}