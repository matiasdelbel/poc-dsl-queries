package com.delbel.poc.dsl.view

import com.delbel.poc.dsl.model.query.Age
import com.delbel.poc.dsl.model.query.Name
import com.delbel.poc.dsl.model.query.isAllowToDrink
import com.delbel.poc.dsl.repository.PeopleRepository

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
