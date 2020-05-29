package com.delbel.person.domain.dsl

class SetStatement : Statement {

    private val values = mutableListOf<String>()

    fun addField(statement: String) = values.add(statement)

    override fun sqlStatement(): String {
        require(values.isNotEmpty()) { "You need to specify at least one field to update" }

        val fieldsToUpdate = values.joinToString(separator = ", ")
        return "SET $fieldsToUpdate"
    }
}
