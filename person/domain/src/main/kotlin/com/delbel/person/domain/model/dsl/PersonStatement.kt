package com.delbel.person.domain.model.dsl

import com.delbel.person.domain.dsl.SetStatement

fun SetStatement.name(query: () -> String) = addField(statement = "name = '${query()}'")

fun SetStatement.age(query: () -> Int) = addField(statement = "age = ${query()}")

fun SetStatement.isAllowToDrink(query: () -> Boolean) = addField(statement = "can_drink = ${query()}")
