package com.delbel.person.domain.gateway

import androidx.sqlite.db.SimpleSQLiteQuery
import com.delbel.person.domain.dsl.UpdateStatement

class PeopleRepository(private val dao: PeopleDao) {

    fun all() = dao.obtainAll()

    fun update(statement: UpdateStatement.() -> Unit) {
        val updateStatement = UpdateStatement().apply(statement)
        val query = SimpleSQLiteQuery(updateStatement.sqlStatement())

        dao.update(query)
    }
}