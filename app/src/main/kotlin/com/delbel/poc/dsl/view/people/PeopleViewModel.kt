package com.delbel.poc.dsl.view.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delbel.poc.dsl.model.Person
import com.delbel.poc.dsl.model.PersonPermission
import com.delbel.poc.dsl.repository.PeopleRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PeopleViewModel @Inject constructor(private val repository: PeopleRepository) : ViewModel() {

    val people = repository.all().asLiveData()

    fun updatePeoplePermission(role: String, isAllow: Boolean) = viewModelScope.launch {
        repository.updateByRole(isAllow = isAllow, role = role)
    }

    fun updatePersonPermission(person: Person, isAllow: Boolean) = viewModelScope.launch {
        repository.updateByPerson(permission = PersonPermission(id = person.id, isAllow = isAllow))
    }
}
