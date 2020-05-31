package com.delbel.poc.dsl.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.delbel.poc.dsl.model.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PeopleDao {

    @Query("SELECT * FROM person")
    fun obtainAll(): Flow<List<Person>>

    @Insert(onConflict = REPLACE)
    fun insertAll(people: List<Person>)

    @RawQuery(observedEntities = [Person::class])
    fun update(query: SupportSQLiteQuery) : Person
}