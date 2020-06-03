package com.delbel.poc.dsl.view.people

import android.content.Context
import android.os.Bundle
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
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

    private val adapter = PeopleAdapter(object : OnPersonSelected {

        override fun onPersonSelected(person: Person) {
            findNavController().navigate(PeopleScreenDirections.toPerson(personId = person.id))
        }

        override fun onPersonIsAllowChanged(person: Person, isAllow: Boolean) {
            viewModel.updatePersonPermission(person, isAllow)
        }
    })

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
        screenBinding.people.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
    }

    private fun submitPeople(people: List<Person>) {
        adapter.submitList(people)
        screenBinding.people.smoothScrollToPosition(0)

        changeChecked(total = people, role = "doctor", view = screenBinding.doctors)
        changeChecked(total = people, role = "administrative", view = screenBinding.administrative)
        changeChecked(total = people, role = "cleaning", view = screenBinding.cleanings)
    }

    private fun changeChecked(total: List<Person>, role: String, view: Chip) {
        val subTotal = total.filter { it.role == role }
        val allowSubTotal = subTotal.filter { it.isAllowToEnter }

        view.setOnCheckedChangeListener(null)
        view.isChecked = subTotal.size == allowSubTotal.size
        view.setOnCheckedChangeListener { _, isChecked -> viewModel.updatePeoplePermission(role = role, isAllow = isChecked) }
    }
}