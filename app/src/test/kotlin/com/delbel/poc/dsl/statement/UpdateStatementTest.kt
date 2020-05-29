package com.delbel.poc.dsl.statement

import com.delbel.poc.dsl.model.query.*
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UpdateStatementTest {

    @Test(expected = IllegalArgumentException::class)
    fun `update with empty set should throw exception`() {
        update { set { } }.sqlStatement()
    }

    @Test
    fun `update with empty where should create sql statement`() {
        val expected = StringBuilder().apply {
            appendln("SET name = 'Matias'")
        }.toString()

        val clause = update {
            set {
                name { "Matias" }
            }
        }.sqlStatement()

        assertThat(clause).isEqualTo(expected)
    }

    @Test
    fun `update with multiple where conditions should create sql statement`() {
        val expected = StringBuilder().apply {
            appendln("SET can_drink = true")
            appendln("WHERE name NOT LIKE John AND id = 12")
        }.toString()

        val clause = update {
            set {
                isAllowToDrink { true }
            }
            where(Name isNot "John", Id isEqualTo 12)
        }.sqlStatement()

        assertThat(clause).isEqualTo(expected)
    }

    @Test
    fun `update with multiple set conditions should create sql statement`() {
        val expected = StringBuilder().apply {
            appendln("SET name = 'Matias', age = 21, can_drink = true")
            appendln("WHERE id = 12")
        }.toString()

        val clause = update {
            set {
                name { "Matias" }
                age { 21 }
                isAllowToDrink { true }
            }
            where(Id isEqualTo 12)
        }.sqlStatement()

        assertThat(clause).isEqualTo(expected)
    }

    @Test
    fun `update with multiple where and set conditions should create sql statement`() {
        val expected = StringBuilder().apply {
            appendln("SET age = 21, can_drink = true")
            appendln("WHERE name NOT LIKE John AND id = 12")
        }.toString()

        val clause = update {
            set {
                age { 21 }
                isAllowToDrink { true }
            }
            where(Name isNot "John", Id isEqualTo 12)
        }.sqlStatement()

        assertThat(clause).isEqualTo(expected)
    }
}