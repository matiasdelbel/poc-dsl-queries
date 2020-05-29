package com.delbel.person.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey val id: Int,
    val name: String,
    val age: Int,
    @ColumnInfo(name = "can_drink")
    val canDrink: Boolean
)