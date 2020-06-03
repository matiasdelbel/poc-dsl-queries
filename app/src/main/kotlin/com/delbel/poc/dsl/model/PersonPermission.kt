package com.delbel.poc.dsl.model

import androidx.room.ColumnInfo

data class PersonPermission(val id: Int, @ColumnInfo(name = "is_allow") val isAllow: Boolean)