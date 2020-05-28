package com.delbel.poc.dsl.queries.di

import com.delbel.poc.dsl.queries.MainApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class])
internal interface MainComponent {

    fun inject(application: MainApplication)

    @Component.Builder
    interface Builder {

        fun build(): MainComponent
    }
}