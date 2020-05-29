package com.delbel.person.domain.dsl

interface Statement {

    fun sqlStatement(): String = ""
}