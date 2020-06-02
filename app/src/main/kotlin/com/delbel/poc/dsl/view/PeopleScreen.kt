package com.delbel.poc.dsl.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatToggleButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.delbel.poc.dsl.R
import com.delbel.poc.dsl.databinding.ScreenPeopleBinding
import com.delbel.poc.dsl.model.Person
import com.google.android.material.chip.Chip
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PeopleScreen : Fragment(R.layout.screen_people) {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: PeopleViewModel by viewModels { viewModelFactory }

    private lateinit var screenBinding: ScreenPeopleBinding
    private val adapter = PeopleAdapter()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpView()

        viewModel.people.observe(viewLifecycleOwner, Observer(::submitPeople))
    }

    private fun setUpView() {
        screenBinding = ScreenPeopleBinding.bind(requireView())

        screenBinding.people.adapter = adapter
        screenBinding.people.addItemDecoration(MarginItemDecoration())

        screenBinding.doctors.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateDoctorsPermission(isAllow = isChecked)
        }
        screenBinding.administrative.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateAdministrativePermission(isAllow = isChecked)
        }
        screenBinding.cleanings.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateCleaningsPermission(isAllow = isChecked)
        }
    }

    private fun submitPeople(people: List<Person>) {
        adapter.submitList(people)

        changeChecked(total = people, role = "doctor", view = screenBinding.doctors)
        changeChecked(total = people, role = "administrative", view = screenBinding.administrative)
        changeChecked(total = people, role = "cleaning", view = screenBinding.cleanings)
    }

    private fun changeChecked(total: List<Person>, role: String, view: Chip) {
        val subTotal = total.filter { it.role == role }
        val allowSubTotal = subTotal.filter { it.isAllowToEnter }

        view.isChecked = subTotal.size == allowSubTotal.size
    }
}