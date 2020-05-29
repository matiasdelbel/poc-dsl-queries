package com.delbel.poc.dsl.statement

class UpdateStatement : Statement {

    private var where: Statement = WhereStatement()
    private var set: Statement = SetStatement()

    fun where(vararg conditions: Condition) {
        val candidate = WhereStatement(conditions = conditions.asList())

        where = candidate
    }

    fun set(query: SetStatement.() -> Unit) {
        val candidate = SetStatement().apply(query)

        set = candidate
    }

    override fun sqlStatement(): String = listOf(set, where)
        .filter { it.sqlStatement().isNotEmpty() }
        .fold(StringBuilder()) { builder, statement ->
            builder.appendln(statement.sqlStatement())
            builder
        }
        .toString()
}

fun update(query: UpdateStatement.() -> Unit): UpdateStatement = UpdateStatement().apply(query)