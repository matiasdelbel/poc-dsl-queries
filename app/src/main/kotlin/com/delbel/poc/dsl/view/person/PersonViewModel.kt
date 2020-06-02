package com.delbel.poc.dsl.view.person

import androidx.lifecycle.*
import com.delbel.poc.dsl.di.AssistedViewModelFactory
import com.delbel.poc.dsl.repository.PeopleRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch

class PersonViewModel @AssistedInject constructor(
    @Assisted handle: SavedStateHandle,
    private val repository: PeopleRepository
) : ViewModel() {

    private val _formStatus = MutableLiveData<FormStatus>()
    val formStatus: LiveData<FormStatus> get() = _formStatus

    val person = repository.obtainBy(id = handle.get<Int>("person_id")!!).asLiveData()

    private val fieldStore = object {
        var name = ""
        var age = 0

        fun isValidName() = name.length > 3

        fun isInvalidName() = !isValidName()

        fun isValidAge() = age >= 18

        fun isInvalidAge() = !isValidAge()
    }

    fun validateName(name: String) {
        fieldStore.name = name

        if (fieldStore.isInvalidName()) _formStatus.value = InvalidName
        else if (fieldStore.isValidAge() && !isOnValidFormStatus()) _formStatus.value = Valid
    }

    fun validateAge(age: Int) {
        fieldStore.age = age

        if (fieldStore.isInvalidAge()) _formStatus.value = InvalidAge
        else if (fieldStore.isValidName() && !isOnValidFormStatus()) _formStatus.value = Valid
    }

    fun update() = viewModelScope.launch {
        require(fieldStore.isValidAge()) { "Invalid age ${fieldStore.age}" }
        require(fieldStore.isValidName()) { "Invalid name ${fieldStore.name}" }

        val person = person.value
        person?.let {
            repository.updatePerson(person = it.with(name = fieldStore.name, age = fieldStore.age))
        }
        _formStatus.value = Submitted
    }

    private fun isOnValidFormStatus() = formStatus.value == Valid

    @AssistedInject.Factory
    interface Factory : AssistedViewModelFactory<PersonViewModel>
}
