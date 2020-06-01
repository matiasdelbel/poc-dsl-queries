package com.delbel.poc.dsl.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.delbel.poc.dsl.R
import com.delbel.poc.dsl.databinding.ScreenPeopleBinding
import com.delbel.poc.dsl.model.Person
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

        screenBinding.everybodyDrink.setOnClickListener { viewModel.everybodyCanDrink() }
        screenBinding.adultsDrinks.setOnClickListener { viewModel.adultsCanDrink() }
        screenBinding.johnDrinks.setOnClickListener { viewModel.onlyJohnCanDrink() }
    }

    private fun submitPeople(people: List<Person>) = adapter.submitList(people)
}