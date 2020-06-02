package com.delbel.poc.dsl.repository

import com.delbel.poc.dsl.R
import com.delbel.poc.dsl.model.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class PeopleProvider @Inject constructor() {

    private val scope = CoroutineScope(Dispatchers.Default)

    fun loadPeopleOn(dao: PeopleDao) = scope.launch { dao.insertAll(people = people) }

    companion object {
        private val people = listOf(
            Person(
                name = "Claude",
                age = 25,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_claude,
                role = "doctor"
            ),
            Person(
                name = "John",
                age = 30,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_john,
                role = "cleaning"
            ),
            Person(
                name = "Lamar",
                age = 37,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_lamar,
                role = "administrative"
            ),
            Person(
                name = "Luke",
                age = 42,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_luke,
                role = "administrative"
            ),
            Person(
                name = "Mary",
                age = 24,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_mary,
                role = "cleaning"
            ),
            Person(
                name = "Mike",
                age = 65,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_mike,
                role = "doctor"
            ),
            Person(
                name = "Monica",
                age = 28,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_monica,
                role = "administrative"
            ),
            Person(
                name = "Serena",
                age = 35,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_serena,
                role = "doctor"
            ),
            Person(
                name = "Steve",
                age = 20,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_steve,
                role = "cleaning"
            )
        )
    }
}