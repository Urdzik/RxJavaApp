package com.example.rxjava_lesson_one.modal.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    var name: String = "name",
    var age: Int = 0
)