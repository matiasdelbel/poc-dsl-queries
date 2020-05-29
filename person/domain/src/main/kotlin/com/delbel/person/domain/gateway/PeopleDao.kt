package com.delbel.person.domain.gateway

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.delbel.person.domain.model.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PeopleDao {

    @Query("SELECT * FROM person")
    fun obtainAll(): Flow<Person>

    @Insert(onConflict = REPLACE)
    fun insertAll(people: List<Person>)

    @RawQuery(observedEntities = [Person::class])
    fun update(query: SupportSQLiteQuery)
}