package com.delbel.poc.dsl.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.delbel.poc.dsl.model.Person
import javax.inject.Singleton

@Singleton
@Database(entities = [Person::class], version = 1, exportSchema = false)
internal abstract class PeopleDatabase : RoomDatabase() {

    abstract fun peopleDao(): PeopleDao
}