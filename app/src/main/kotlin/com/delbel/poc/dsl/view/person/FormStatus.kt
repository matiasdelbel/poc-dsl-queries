package com.delbel.poc.dsl.view.person

sealed class FormStatus

object InvalidName : FormStatus()

object InvalidAge : FormStatus()

object Valid : FormStatus()

object Submitted : FormStatus()