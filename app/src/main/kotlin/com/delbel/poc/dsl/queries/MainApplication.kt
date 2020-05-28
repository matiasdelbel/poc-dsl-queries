package com.delbel.poc.dsl.queries

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import com.delbel.poc.dsl.queries.di.DaggerMainComponent

class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    override fun androidInjector() = androidInjector

    private fun injectDependencies() = DaggerMainComponent.builder()
        .build()
        .inject(application = this)
}