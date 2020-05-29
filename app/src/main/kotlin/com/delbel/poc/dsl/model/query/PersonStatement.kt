package com.delbel.poc.dsl.model.query

import com.delbel.poc.dsl.statement.SetStatement

fun SetStatement.name(query: () -> String) = addField(statement = "name = '${query()}'")

fun SetStatement.age(query: () -> Int) = addField(statement = "age = ${query()}")

fun SetStatement.isAllowToDrink(query: () -> Boolean) = addField(statement = "can_drink = ${query()}")
