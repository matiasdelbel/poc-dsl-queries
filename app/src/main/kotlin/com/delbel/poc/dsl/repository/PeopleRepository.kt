package com.delbel.poc.dsl.repository

import com.delbel.poc.dsl.model.Person
import com.delbel.poc.dsl.model.PersonRole
import javax.inject.Inject

class PeopleRepository @Inject constructor(private val dao: PeopleDao) {

    fun all() = dao.obtainAll()

    fun obtainBy(id: Int) = dao.obtainBy(id)

    suspend fun updateByRole(isAllow: Boolean, role: String) = dao.updateByRole(isAllow, role)

    suspend fun updateRole(personRole: PersonRole) = dao.updateRole(personRole)

    suspend fun updatePerson(person: Person) = dao.updatePerson(person)
}