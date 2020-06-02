package com.delbel.poc.dsl.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.delbel.poc.dsl.repository.PeopleRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PeopleViewModel @Inject constructor(private val repository: PeopleRepository) : ViewModel() {

    val people = repository.all().asLiveData()

    fun updateDoctorsPermission(isAllow: Boolean) = viewModelScope.launch {
        repository.updateByRole(isAllow = isAllow, role = "doctor")
    }

    fun updateAdministrativePermission(isAllow: Boolean) = viewModelScope.launch {
        repository.updateByRole(isAllow = isAllow, role = "administrative")
    }

    fun updateCleaningsPermission(isAllow: Boolean) = viewModelScope.launch {
        repository.updateByRole(isAllow = isAllow, role = "cleaning")
    }

}
