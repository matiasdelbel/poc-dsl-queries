package com.delbel.poc.dsl.repository

import com.delbel.poc.dsl.R
import com.delbel.poc.dsl.model.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class PeoplePreLoader @Inject constructor() {

    private val scope = CoroutineScope(Dispatchers.Default)

    fun loadPeople(dao: PeopleDao) = scope.launch { dao.insertAll(people = people) }

    companion object {
        private val people = listOf(
            Person(name = "Claude", age = 25, canDrink = false, avatar = R.drawable.avatar_claude),
            Person(name = "John", age = 30, canDrink = false, avatar = R.drawable.avatar_john),
            Person(name = "Lamar", age = 21, canDrink = false, avatar = R.drawable.avatar_lamar),
            Person(name = "Luke", age = 18, canDrink = false, avatar = R.drawable.avatar_luke),
            Person(name = "Mary", age = 34, canDrink = false, avatar = R.drawable.avatar_mary),
            Person(name = "Mike", age = 65, canDrink = false, avatar = R.drawable.avatar_mike),
            Person(name = "Monica", age = 22, canDrink = false, avatar = R.drawable.avatar_monica),
            Person(name = "Serena", age = 19, canDrink = false, avatar = R.drawable.avatar_serena),
            Person(name = "Steve", age = 20, canDrink = false, avatar = R.drawable.avatar_steve)
        )
    }
}