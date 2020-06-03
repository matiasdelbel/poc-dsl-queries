package com.delbel.poc.dsl.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.delbel.poc.dsl.model.Person
import com.delbel.poc.dsl.model.PersonPermission
import com.delbel.poc.dsl.model.Role
import kotlinx.coroutines.flow.Flow

@Dao
interface PeopleDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(people: List<Person>)

    @Query("SELECT * FROM people ORDER BY is_allow DESC, role ASC, name ASC")
    fun obtainAll(): Flow<List<Person>>

    @Query("SELECT * FROM people WHERE id = :id LIMIT 1")
    fun obtainBy(id: Int): Flow<Person>

    @Query("UPDATE people SET is_allow = :is_allow WHERE role = :role")
    suspend fun updateByRole(is_allow: Boolean, role: Role)

    @Update(entity = Person::class)
    suspend fun updatePermission(permission: PersonPermission)

    @Update
    suspend fun updatePerson(person: Person)
}