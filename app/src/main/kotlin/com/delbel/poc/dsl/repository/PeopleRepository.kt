package com.delbel.poc.dsl.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import com.delbel.poc.dsl.statement.UpdateStatement
import javax.inject.Inject

class PeopleRepository @Inject constructor(private val dao: PeopleDao) {

    fun all() = dao.obtainAll()

    suspend fun update(statement: UpdateStatement.() -> Unit) {
        val updateStatement = UpdateStatement().apply(statement)
        val query = SimpleSQLiteQuery(updateStatement.sqlStatement())

        dao.update(query)
    }
}