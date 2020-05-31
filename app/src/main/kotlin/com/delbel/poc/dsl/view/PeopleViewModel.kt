package com.delbel.poc.dsl.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delbel.poc.dsl.model.query.Age
import com.delbel.poc.dsl.model.query.Name
import com.delbel.poc.dsl.model.query.isAllowToDrink
import com.delbel.poc.dsl.repository.PeopleRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PeopleViewModel @Inject constructor(private val repository: PeopleRepository) : ViewModel() {

    val people = repository.all().asLiveData()

    fun everybodyCanDrink() = viewModelScope.launch {
        repository.update {
            set { isAllowToDrink { true } }
        }
    }

    fun adultsCanDrink() = viewModelScope.launch {
        repository.update {
            where(Age isYoungerThan 21)
            set { isAllowToDrink { false } }
        }
    }

    fun onlyJohnCanDrink() = viewModelScope.launch {
        repository.update {
            where(Name isNot "John")
            set { isAllowToDrink { false } }
        }
    }
}
