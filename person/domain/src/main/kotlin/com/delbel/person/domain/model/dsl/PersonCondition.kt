package com.delbel.person.domain.model.dsl

import com.delbel.person.domain.dsl.Condition

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