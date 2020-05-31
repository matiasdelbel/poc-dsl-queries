package com.delbel.poc.dsl.di

import androidx.lifecycle.ViewModel
import com.delbel.dagger.viewmodel.general.ViewModelKey
import com.delbel.dagger.viewmodel.general.di.ViewModelFactoryModule
import com.delbel.poc.dsl.view.PeopleScreen
import com.delbel.poc.dsl.view.PeopleViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
interface ViewModule {

    @Binds
    @IntoMap
    @ViewModelKey(PeopleViewModel::class)
    fun bindPeopleViewModel(viewModel: PeopleViewModel): ViewModel

    @ContributesAndroidInjector
    fun contributePeopleScreen(): PeopleScreen
}