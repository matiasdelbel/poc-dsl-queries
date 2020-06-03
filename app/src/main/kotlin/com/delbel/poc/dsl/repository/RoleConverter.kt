package com.delbel.poc.dsl.repository

import androidx.room.TypeConverter
import com.delbel.poc.dsl.model.Role

class RoleConverter {

    @TypeConverter
    fun fromValueToRole(value: String) = Role.valueOf(value.toUpperCase())

    @TypeConverter
    fun fromRoleToValue(date: Role?) = date?.value
}