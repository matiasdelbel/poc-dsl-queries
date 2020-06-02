package com.delbel.poc.dsl.model

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class Person(
    val name: String,
    val age: Int,
    val role: String,
    @ColumnInfo(name = "is_allow")
    val isAllowToEnter: Boolean,
    @DrawableRes
    val avatar: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}