package com.delbel.poc.dsl.repository

import com.delbel.poc.dsl.R
import com.delbel.poc.dsl.model.Person
import com.delbel.poc.dsl.model.Role
import com.delbel.poc.dsl.model.Role.*
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
                name = "Agatha",
                age = 25,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_claude,
                role = DOCTOR
            ),
            Person(
                name = "John",
                age = 30,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_john,
                role = NURSE
            ),
            Person(
                name = "Lamar",
                age = 37,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_lamar,
                role = DENTIST
            ),
            Person(
                name = "Luke",
                age = 42,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_luke,
                role = DENTIST
            ),
            Person(
                name = "Mary",
                age = 24,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_mary,
                role = NURSE
            ),
            Person(
                name = "Mike",
                age = 65,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_mike,
                role = DOCTOR
            ),
            Person(
                name = "Monica",
                age = 28,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_monica,
                role = DENTIST
            ),
            Person(
                name = "Serena",
                age = 35,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_serena,
                role = DOCTOR
            ),
            Person(
                name = "Steve",
                age = 20,
                isAllowToEnter = false,
                avatar = R.drawable.avatar_steve,
                role = NURSE
            )
        )
    }
}