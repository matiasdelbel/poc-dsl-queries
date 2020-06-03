package com.delbel.poc.dsl.view.person

import android.content.Context
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.delbel.dagger.viewmodel.savedstate.ext.viewModels
import com.delbel.poc.dsl.R
import com.delbel.poc.dsl.databinding.ScreenPersonBinding
import com.delbel.poc.dsl.model.Person
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PersonScreen : Fragment(R.layout.screen_person) {

    @Inject
    internal lateinit var factory: PersonViewModel.Factory
    private val viewModel: PersonViewModel by viewModels { factory }

    private lateinit var screenBinding: ScreenPersonBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpView()

        viewModel.person.observe(viewLifecycleOwner, Observer(::setUpPerson))
        viewModel.formStatus.observe(viewLifecycleOwner, Observer(::setUpFormStatus))
    }

    private fun setUpView() {
        screenBinding = ScreenPersonBinding.bind(requireView())

        screenBinding.personNameText.addTextChangedListener {
            viewModel.validateName(name = it.toString())
        }
        screenBinding.personAgeText.addTextChangedListener {
            val candidate = if (it.toString().isEmpty()) 0 else it.toString().toInt()
            viewModel.validateAge(age = candidate)
        }

        screenBinding.savePerson.setOnClickListener {
            screenBinding.progress.isVisible = true
            viewModel.update()
        }
    }

    private fun setUpPerson(person: Person) {
        screenBinding.personNameText.setText(person.name)
        screenBinding.personAgeText.setText("${person.age}")

        screenBinding.progress.isVisible = false
    }

    private fun setUpFormStatus(formStatus: FormStatus) {
        when (formStatus) {
            InvalidName -> screenBinding.personName.error = getString(R.string.person_name_error)
            InvalidAge -> screenBinding.personAge.error = getString(R.string.person_age_error)
            Valid -> setUpValidStatus()
            Submitted -> findNavController().popBackStack()
        }
    }

    private fun setUpValidStatus() {
        screenBinding.personName.helperText = getString(R.string.person_name_helper_text)
        screenBinding.personAge.helperText = getString(R.string.person_age_helper_text)
        screenBinding.savePerson.isEnabled = true
    }
}