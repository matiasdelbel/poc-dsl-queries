package com.delbel.poc.dsl.di

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.delbel.dagger.viewmodel.general.ViewModelKey
import com.delbel.dagger.viewmodel.general.di.ViewModelFactoryModule
import com.delbel.dagger.viewmodel.savedstate.ViewModelFactory
import com.delbel.poc.dsl.view.people.PeopleScreen
import com.delbel.poc.dsl.view.people.PeopleViewModel
import com.delbel.poc.dsl.view.person.PersonScreen
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class, AssistedViewModelModule::class])
interface ViewModule {

    @Binds
    @IntoMap
    @ViewModelKey(PeopleViewModel::class)
    fun bindPeopleViewModel(viewModel: PeopleViewModel): ViewModel

    @ContributesAndroidInjector
    fun contributePeopleScreen(): PeopleScreen

    @ContributesAndroidInjector
    fun contributePersonScreen(): PersonScreen
}

@AssistedModule
@Module(includes = [AssistedInject_AssistedViewModelModule::class])
interface AssistedViewModelModule

interface AssistedViewModelFactory<T : ViewModel> : ViewModelFactory<T> {

    override fun create(handle: SavedStateHandle): T
}