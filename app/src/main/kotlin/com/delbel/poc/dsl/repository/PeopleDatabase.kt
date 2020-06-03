package com.delbel.poc.dsl.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.delbel.poc.dsl.model.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
@TypeConverters(RoleConverter::class)
abstract class PeopleDatabase : RoomDatabase() {

    abstract fun peopleDao(): PeopleDao
}