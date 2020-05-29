package com.delbel.person.domain.dsl

class WhereStatement(private val conditions: List<Condition> = emptyList()) : Statement {

    override fun sqlStatement(): String {
        if (conditions.isEmpty()) return EMPTY_WHERE_CLAUSE

        val filteringFields = conditions.joinToString(separator = " AND ") { it.sqlStatement() }
        return "WHERE $filteringFields"
    }

    companion object {
        private const val EMPTY_WHERE_CLAUSE = ""
    }
}

abstract class Condition : Statement {

    protected var clause: String = ""

    override fun sqlStatement(): String = clause
}
