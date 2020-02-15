package com.example.rxjava_lesson_one.modal.database

import io.reactivex.Flowable
import io.reactivex.Single

class LocalUserData(val user: User) : UserDatabaseDAO {


    override fun insert(user: User) {

    }

    override fun getAll(): Flowable<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllWithId(id: Long): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}