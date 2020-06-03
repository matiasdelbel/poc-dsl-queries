package com.delbel.poc.dsl.repository

import com.delbel.poc.dsl.model.Person
import com.delbel.poc.dsl.model.PersonPermission
import com.delbel.poc.dsl.model.Role
import javax.inject.Inject

class PeopleRepository @Inject constructor(private val dao: PeopleDao) {

    fun all() = dao.obtainAll()

    fun obtainBy(id: Int) = dao.obtainBy(id)

    suspend fun updatePerson(person: Person) = dao.updatePerson(person)

    suspend fun updateByRole(isAllow: Boolean, role: Role) = dao.updateByRole(isAllow, role)

    suspend fun updateByPerson(permission: PersonPermission) = dao.updatePermission(permission)
}