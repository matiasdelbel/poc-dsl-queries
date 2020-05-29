package com.delbel.poc.dsl.statement

interface Statement {

    fun sqlStatement(): String = ""
}