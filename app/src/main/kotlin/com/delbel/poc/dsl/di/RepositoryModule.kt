package com.delbel.poc.dsl.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.delbel.poc.dsl.repository.PeopleDatabase
import com.delbel.poc.dsl.repository.PeoplePreLoader
import dagger.Module
import dagger.Provides

@Module
internal class RepositoryModule {

    companion object {
        private const val DATABASE_NAME = "people.db"
    }

    @Provides
    fun provideDatabase(
        context: Application,
        peoplePreLoader: PeoplePreLoader
    ): PeopleDatabase = with(mutableListOf<PeopleDatabase>()) {

        val database = Room.databaseBuilder(context, PeopleDatabase::class.java, DATABASE_NAME)
            .addCallback(object : RoomDatabase.Callback() {

                override fun onCreate(db: SupportSQLiteDatabase) {
                    peoplePreLoader.loadPeople(dao = this@with.first().peopleDao())
                }
            })
            .build()
        add(database)

        first()
    }

    @Provides
    fun providePeopleDao(database: PeopleDatabase) = database.peopleDao()
}