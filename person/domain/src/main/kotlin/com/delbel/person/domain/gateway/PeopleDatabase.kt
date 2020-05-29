package com.delbel.person.domain.gateway

import androidx.room.Database
import androidx.room.RoomDatabase
import com.delbel.person.domain.model.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
internal abstract class PeopleDatabase : RoomDatabase() {

    abstract fun personDao(): PeopleDao
}