package com.delbel.poc.dsl.model

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    val name: String,
    val age: Int,
    @ColumnInfo(name = "can_drink") val canDrink: Boolean,
    @DrawableRes val avatar: Int
) {

    @PrimaryKey
    var id: Int = Int.MAX_VALUE
}