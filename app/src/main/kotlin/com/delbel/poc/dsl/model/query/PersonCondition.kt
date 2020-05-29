package com.delbel.poc.dsl.model.query

import com.delbel.poc.dsl.statement.Condition

object Id : Condition() {

    infix fun isEqualTo(value: Int): Condition {
        clause = "id = $value"

        return this
    }
}

object Age : Condition() {

    infix fun isYoungerThan(value: Int): Condition {
        clause = "age <= $value"

        return this
    }
}

object Name : Condition() {

    infix fun isNot(value: String): Condition {
        clause = "name NOT LIKE $value"

        return this
    }
}