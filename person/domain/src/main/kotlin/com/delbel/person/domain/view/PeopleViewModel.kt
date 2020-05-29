package com.delbel.person.domain.view

import com.delbel.person.domain.gateway.PeopleRepository
import com.delbel.person.domain.model.dsl.Age
import com.delbel.person.domain.model.dsl.Name
import com.delbel.person.domain.model.dsl.isAllowToDrink

class PeopleViewModel(private val repository: PeopleRepository) {

    fun allCanDrink() = repository.update {
        set {
            isAllowToDrink { true }
        }
    }

    fun adultsCanDrink() = repository.update {
        where(Age isYoungerThan 21)
        set {
            isAllowToDrink { false }
        }
    }

    fun onlyJohnDrinks() = repository.update {
        where(Name isNot "John")
        set {
            isAllowToDrink { false }
        }
    }
}
